package dev.brokenbytes.hykore.plugins

import com.hypixel.hytale.component.ComponentRegistryProxy
import com.hypixel.hytale.event.EventRegistry
import com.hypixel.hytale.server.core.modules.entity.EntityRegistration
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import dev.brokenbytes.hykore.ecs.ComponentLifecycleLoader
import dev.brokenbytes.hykore.ecs.EcsHandlerLoader
import dev.brokenbytes.hykoreapi.PluginContext

class PluginContextImpl(
    private val ecsLoader: EcsHandlerLoader,
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
