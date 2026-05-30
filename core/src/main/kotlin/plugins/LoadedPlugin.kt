package dev.brokenbytes.hykore.plugins

import dev.brokenbytes.hykoreapi.HyKorePlugin
import dev.brokenbytes.hykoreapi.PluginContext
import java.net.URLClassLoader

class LoadedPlugin(
    val id: String,
    val instance: HyKorePlugin,
    val classLoader: URLClassLoader,
    val context: PluginContext
)