package dev.brokenbytes.hykore.plugins

import dev.brokenbytes.hykore.ecs.ComponentLifecycleLoader
import dev.brokenbytes.hykore.ecs.EventHandlerLoader
import dev.brokenbytes.hykoreapi.PluginContext

class PluginContextImpl(
    private val ecsLoader: EventHandlerLoader,
    private val componentLifecycleLoader: ComponentLifecycleLoader,
    private val pluginKey: Any
) : PluginContext {

    override fun registerEcsEventHandler(listener: Any) {
        ecsLoader.registerEvents(pluginKey, listener)
    }

    override fun registerComponentLifecycleHandler(listener: Any) {
        componentLifecycleLoader.register(pluginKey, listener)
    }
}
