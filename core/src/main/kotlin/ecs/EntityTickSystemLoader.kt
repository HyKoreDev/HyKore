package dev.brokenbytes.hykore.ecs

import com.hypixel.hytale.component.ComponentRegistryProxy
import com.hypixel.hytale.logger.HytaleLogger
import com.hypixel.hytale.server.core.universe.world.World
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import dev.brokenbytes.hykore.mappers.EcsEventMapper
import dev.brokenbytes.hykoreapi.annotations.OnEntityTick
import dev.brokenbytes.hykoreapi.annotations.OnEvent
import dev.brokenbytes.hykoreapi.ecs.events.EcsEvent
import java.lang.reflect.Method

class EntityTickSystemLoader(
    private val entityStoreRegistry: ComponentRegistryProxy<EntityStore>,
    private val logger: HytaleLogger
) {
    private data class Hub(
        val system: EventHandler<*>,
        val handlers: MutableList<(dev.brokenbytes.hykoreapi.core.World, EcsEvent) -> Unit>,
    )
    private val hubs = mutableMapOf<Class<out EcsEvent>, Hub>()

    private val byPlugin = mutableMapOf<Any, MutableList<Pair<Class<out EcsEvent>, (dev.brokenbytes.hykoreapi.core.World, EcsEvent) -> Unit>>>()

    fun registerEntitySystem(pluginKey: Any, listener: Any) {
        listener::class.java.methods
            .filter { it.isAnnotationPresent(OnEntityTick::class.java) }
            .forEach { method -> bind(pluginKey, listener, method) }
    }

    private fun bind(pluginKey: Any, listener: Any, method: Method) {
        val eventClass = method.parameterTypes
            .firstOrNull { OnEntityTick::class.java.isAssignableFrom(it) }
            ?: error("@OnEcsEvent ${method.declaringClass.simpleName}.${method.name} " +
                    "must take an EntityTick parameter")

        @Suppress("UNCHECKED_CAST")
        val evtClass = eventClass as Class<out EcsEvent>

        method.isAccessible = true

        val invoker: (dev.brokenbytes.hykoreapi.core.World, EcsEvent) -> Unit = { world, event ->
            val args = method.parameters.mapIndexed { i, p ->
                when {
                    EcsEvent::class.java.isAssignableFrom(p.type) -> event
                    p.type == dev.brokenbytes.hykoreapi.core.World::class.java -> world
                    else -> error("@OnEcsEvent ${method.name}: unsupported parameter ${p.type}")
                }
            }.toTypedArray()
            method.invoke(listener, *args)
        }

        val hub = hubs.getOrPut(evtClass) { createHub(evtClass) }
        hub.handlers += invoker

        byPlugin.getOrPut(pluginKey) { mutableListOf() } += evtClass to invoker
    }

    private fun createHub(evtClass: Class<out EcsEvent>): Hub {
        val handlers = mutableListOf<(dev.brokenbytes.hykoreapi.core.World, EcsEvent) -> Unit>()
        @Suppress("UNCHECKED_CAST")
        val clazz = EcsEventMapper.to(evtClass)
        val system = EventHandler(clazz) { world, event ->
            handlers.toList().forEach { it(world, EcsEventMapper.from(event)) }
        }
        entityStoreRegistry.registerSystem(system)

        return Hub(system, handlers)
    }

    fun unregister(pluginKey: Any) {
        byPlugin.remove(pluginKey)?.forEach { (evtClass, invoker) ->
            hubs[evtClass]?.handlers?.remove(invoker)
        }
    }
}