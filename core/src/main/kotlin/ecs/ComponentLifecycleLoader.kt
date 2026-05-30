package dev.brokenbytes.hykore.ecs

import com.hypixel.hytale.component.ComponentRegistryProxy
import com.hypixel.hytale.logger.HytaleLogger
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import dev.brokenbytes.hykore.mappers.ComponentMapper
import dev.brokenbytes.hykoreapi.annotations.OnComponentAdd
import dev.brokenbytes.hykoreapi.annotations.OnComponentRemove
import dev.brokenbytes.hykoreapi.annotations.OnComponentSet
import dev.brokenbytes.hykoreapi.core.Entity
import dev.brokenbytes.hykoreapi.core.World
import dev.brokenbytes.hykoreapi.ecs.components.EcsComponent
import java.lang.reflect.Method

class ComponentLifecycleLoader(
    private val entityStoreRegistry: ComponentRegistryProxy<EntityStore>,
    private val logger: HytaleLogger,
) {

    private class Hub(
        val system: ComponentLifecycleHandler,
        val onAdd: MutableList<(World, Entity, EcsComponent) -> Unit> = mutableListOf(),
        val onSet: MutableList<(World, Entity, EcsComponent?, EcsComponent) -> Unit> = mutableListOf(),
        val onRemove: MutableList<(World, Entity, EcsComponent) -> Unit> = mutableListOf(),
    )

    private val hubs = mutableMapOf<Class<out EcsComponent>, Hub>()

    // per-plugin detach actions so unregister() can remove every handler it added
    private val byPlugin = mutableMapOf<Any, MutableList<() -> Unit>>()

    fun register(pluginKey: Any, listener: Any) {
        val cls = listener::class.java

        cls.methods.forEach { method ->
            when {
                method.isAnnotationPresent(OnComponentAdd::class.java) ->
                    bindAdd(pluginKey, listener, method)

                method.isAnnotationPresent(OnComponentSet::class.java) ->
                    bindSet(pluginKey, listener, method)

                method.isAnnotationPresent(OnComponentRemove::class.java) ->
                    bindRemove(pluginKey, listener, method)
            }
        }
    }

    private fun bindAdd(pluginKey: Any, listener: Any, method: Method) {
        val componentClass = componentParam(method, "OnComponentAdd")
        method.isAccessible = true

        val invoker: (World, Entity, EcsComponent) -> Unit = { world, entity, component ->
            method.invoke(listener, world, entity, component)
        }
        val hub = hubs.getOrPut(componentClass) { createHub(componentClass) }
        hub.onAdd += invoker
        detachOnUnload(pluginKey) { hub.onAdd.remove(invoker) }
    }

    private fun bindRemove(pluginKey: Any, listener: Any, method: Method) {
        val componentClass = componentParam(method, "OnComponentRemove")
        method.isAccessible = true

        val invoker: (World, Entity, EcsComponent) -> Unit = { world, entity, component ->
            method.invoke(listener, world, entity, component)
        }
        val hub = hubs.getOrPut(componentClass) { createHub(componentClass) }
        hub.onRemove += invoker
        detachOnUnload(pluginKey) { hub.onRemove.remove(invoker) }
    }

    private fun bindSet(pluginKey: Any, listener: Any, method: Method) {
        require(method.parameterCount == 4) {
            "@OnComponentSet ${signature(method)} must take (World, Entity, previous, current)"
        }

        val (worldType, entityType) = method.parameterTypes
        requireContext(method, "OnComponentSet", worldType, entityType)

        val previousType = method.parameterTypes[2]
        val currentType = method.parameterTypes[3]

        require(
            EcsComponent::class.java.isAssignableFrom(previousType) &&
                EcsComponent::class.java.isAssignableFrom(currentType)
        ) {
            "@OnComponentSet ${signature(method)} previous/current must both be EcsComponent subtypes, " +
                "got (${previousType.name}, ${currentType.name})"
        }

        require(previousType == currentType) {
            "@OnComponentSet ${signature(method)} previous and current must be the same component type, " +
                "got ${previousType.simpleName} and ${currentType.simpleName}"
        }

        @Suppress("UNCHECKED_CAST")
        val componentClass = currentType as Class<out EcsComponent>
        method.isAccessible = true

        val invoker: (World, Entity, EcsComponent?, EcsComponent) -> Unit = { world, entity, previous, current ->
            method.invoke(listener, world, entity, previous, current)
        }
        val hub = hubs.getOrPut(componentClass) { createHub(componentClass) }
        hub.onSet += invoker
        detachOnUnload(pluginKey) { hub.onSet.remove(invoker) }
    }

    /** Validates an add/remove method is `(World, Entity, <EcsComponent>)` and returns the component type. */
    private fun componentParam(method: Method, annotation: String): Class<out EcsComponent> {
        require(method.parameterCount == 3) {
            "@$annotation ${signature(method)} must take (World, Entity, <component>)"
        }

        val (worldType, entityType) = method.parameterTypes
        requireContext(method, annotation, worldType, entityType)

        val componentType = method.parameterTypes[2]
        require(EcsComponent::class.java.isAssignableFrom(componentType)) {
            "@$annotation ${signature(method)} third parameter must be an EcsComponent subtype, got ${componentType.name}"
        }

        @Suppress("UNCHECKED_CAST")
        return componentType as Class<out EcsComponent>
    }

    private fun requireContext(method: Method, annotation: String, worldType: Class<*>, entityType: Class<*>) {
        require(worldType == World::class.java && entityType == Entity::class.java) {
            "@$annotation ${signature(method)} must start with (World, Entity), got (${worldType.simpleName}, ${entityType.simpleName})"
        }
    }

    private fun signature(method: Method) = "${method.declaringClass.simpleName}.${method.name}"

    private fun detachOnUnload(pluginKey: Any, action: () -> Unit) {
        byPlugin.getOrPut(pluginKey) { mutableListOf() } += action
    }

    private fun createHub(componentClass: Class<out EcsComponent>): Hub {
        val onAdd = mutableListOf<(World, Entity, EcsComponent) -> Unit>()
        val onSet = mutableListOf<(World, Entity, EcsComponent?, EcsComponent) -> Unit>()
        val onRemove = mutableListOf<(World, Entity, EcsComponent) -> Unit>()

        val system = ComponentLifecycleHandler(
            ComponentMapper.toComponentType(componentClass),
            onAdded = { world, entity, component -> onAdd.toList().forEach { it(world, entity, component) } },
            onSet = { world, entity, previous, component -> onSet.toList().forEach { it(world, entity, previous, component) } },
            onRemoved = { world, entity, component -> onRemove.toList().forEach { it(world, entity, component) } },
        )
        entityStoreRegistry.registerSystem(system)

        return Hub(system, onAdd, onSet, onRemove)
    }

    fun unregister(pluginKey: Any) {
        byPlugin.remove(pluginKey)?.forEach { it() }
    }
}
