package dev.brokenbytes.hykore.ecs

import com.hypixel.hytale.component.*
import com.hypixel.hytale.component.query.Query
import com.hypixel.hytale.component.system.RefChangeSystem
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import dev.brokenbytes.hykore.core.WorldImpl
import dev.brokenbytes.hykore.mappers.ComponentMapper
import dev.brokenbytes.hykoreapi.ecs.Entity
import dev.brokenbytes.hykoreapi.core.World
import dev.brokenbytes.hykoreapi.ecs.components.EcsComponent

class ComponentLifecycleHandler(
    private val type: ComponentType<EntityStore, Component<EntityStore>>,
    private val onAdded: (World, Entity, EcsComponent) -> Unit,
    private val onSet: (World, Entity, EcsComponent?, EcsComponent) -> Unit,
    private val onRemoved: (World, Entity, EcsComponent) -> Unit,
) : RefChangeSystem<EntityStore, Component<EntityStore>>() {

    override fun componentType(): ComponentType<EntityStore, Component<EntityStore>> = type

    override fun onComponentAdded(
        ref: Ref<EntityStore>,
        component: Component<EntityStore>,
        store: Store<EntityStore>,
        commandBuffer: CommandBuffer<EntityStore>,
    ) {
        onAdded(WorldImpl.from(store.externalData.world), EntityImpl(ref), ComponentMapper.from(component))
    }

    override fun onComponentSet(
        ref: Ref<EntityStore?>,
        previous: Component<EntityStore>?,
        component: Component<EntityStore>,
        store: Store<EntityStore?>,
        commandBuffer: CommandBuffer<EntityStore?>
    ) {
        @Suppress("UNCHECKED_CAST")
        onSet(
            WorldImpl.from(store.externalData.world),
            EntityImpl(ref as Ref<EntityStore>),
            previous?.let { ComponentMapper.from(it) },
            ComponentMapper.from(component),
        )
    }

    override fun onComponentRemoved(
        ref: Ref<EntityStore>,
        component: Component<EntityStore>,
        store: Store<EntityStore>,
        commandBuffer: CommandBuffer<EntityStore>,
    ) {
        onRemoved(WorldImpl.from(store.externalData.world), EntityImpl(ref), ComponentMapper.from(component))
    }

    override fun getQuery(): Query<EntityStore?>? = Archetype.empty()
}
