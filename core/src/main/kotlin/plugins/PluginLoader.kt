package dev.brokenbytes.hykore.plugins

import com.hypixel.hytale.logger.HytaleLogger
import dev.brokenbytes.hykore.ecs.ComponentLifecycleLoader
import dev.brokenbytes.hykore.ecs.EcsHandlerLoader
import dev.brokenbytes.hykoreapi.HyKorePlugin
import kotlinx.serialization.json.Json
import java.io.File
import java.net.URLClassLoader
import java.util.jar.JarFile

class PluginLoader(
    private val logger: HytaleLogger,
    private val ecsLoader: EcsHandlerLoader,
    private val componentLifecycleLoader: ComponentLifecycleLoader,
    private val parentClassLoader: ClassLoader,
) {

    private val loaded = mutableMapOf<String, LoadedPlugin>()
    private val json = Json { ignoreUnknownKeys = true; isLenient = true }

    fun loadAll() {
        val myJar = File(
            javaClass.protectionDomain.codeSource.location.toURI()
        )
        val modsDir = myJar.parentFile
        val serverRoot = modsDir.parentFile
        val hykoreDir = File(serverRoot, "hykore")

        val jarUrls = hykoreDir.listFiles { f -> f.extension == "jar" }

        val sharedLoader = URLClassLoader(
            jarUrls?.map { it.toURI().toURL() }?.toTypedArray(),
            HyKorePlugin::class.java.classLoader
        )

        jarUrls?.forEach { jar ->
            runCatching { load(jar, sharedLoader) }
                .onFailure {
                    logger.atSevere().log("failed to load ${jar.name}: ${it.message}")
                }
        }
    }

    private fun load(jar: File, loader: URLClassLoader) {
        val manifest = readManifest(jar)
            ?: error("${jar.name}: missing hykore manifest")

        logger.atInfo().log("Loading plugin: ${manifest.name}")

        if (loaded.containsKey(manifest.id)) {
            logger.atSevere().log("duplicate plugin id '${manifest.id}' (${jar.name})")
            error("duplicate plugin id '${manifest.id}' (${jar.name})")
        }

        val clazz = loader.loadClass(manifest.main)
        require(HyKorePlugin::class.java.isAssignableFrom(clazz)) {
            "${manifest.main} must implement HykorePlugin"
        }

        val instance = clazz.getDeclaredConstructor().newInstance() as HyKorePlugin

        logger.atInfo().log("HyKore: Setup Plugin ${manifest.name}")

        val context = PluginContextImpl(ecsLoader, componentLifecycleLoader, jar.name)

        instance.setup(context)

        loaded[manifest.id] = LoadedPlugin(manifest.id, instance, loader, context)

        logger.atInfo().log("HyKore: Start Plugin ${manifest.name}")

        instance.start(context)
    }

    fun unloadAll() = loaded.keys.toList().forEach(::unload)

    fun unload(id: String) {
        val p = loaded.remove(id) ?: return
        runCatching { p.instance.teardown() }
        ecsLoader.unregister(id)
        componentLifecycleLoader.unregister(id)
        p.classLoader.close()
    }

    private fun readManifest(jar: File): HyKoreManifest? {
        val text = JarFile(jar).use { jf ->
            val entry = jf.getJarEntry("hykore.json") ?: return null
            jf.getInputStream(entry).use { it.readBytes().decodeToString() }
        }
        return runCatching { json.decodeFromString<HyKoreManifest>(text) }
            .getOrElse { null }

    }
}