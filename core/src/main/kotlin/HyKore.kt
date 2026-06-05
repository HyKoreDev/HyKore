package dev.brokenbytes.hykore

import com.hypixel.hytale.server.core.plugin.JavaPlugin
import com.hypixel.hytale.server.core.plugin.JavaPluginInit
import dev.brokenbytes.hykore.ecs.ComponentLifecycleLoader
import dev.brokenbytes.hykore.ecs.EventHandlerLoader
import dev.brokenbytes.hykore.plugins.PluginLoader
import dev.brokenbytes.hykoreapi.HyKorePlugin

class HyKore(val plugin: JavaPluginInit) : JavaPlugin(plugin) {

    private lateinit var ecsHandlerLoader: EventHandlerLoader
    private lateinit var componentLifecycleLoader: ComponentLifecycleLoader
    private lateinit var pluginLoader: PluginLoader

    override fun setup() {
        super.setup()

        ecsHandlerLoader = EventHandlerLoader(entityStoreRegistry, logger)
        componentLifecycleLoader = ComponentLifecycleLoader(entityStoreRegistry, logger)
        pluginLoader = PluginLoader(
            logger,
            ecsHandlerLoader,
            componentLifecycleLoader,
            HyKorePlugin::class.java.classLoader
        )
    }

    override fun start() {
        super.start()

        logger.atInfo().log("HyKore: Loading Plugins")

        pluginLoader.loadAll()

        logger.atInfo().log("HyKore: Plugins loaded")
    }

    override fun shutdown() {
        super.shutdown()

        pluginLoader.unloadAll()
    }
}