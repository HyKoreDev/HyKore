package dev.brokenbytes.hykore.ecs

import com.hypixel.hytale.component.ArchetypeChunk
import com.hypixel.hytale.component.CommandBuffer
import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import dev.brokenbytes.hykore.mappers.ComponentMapper
import dev.brokenbytes.hykoreapi.ecs.EntityTickRef
import dev.brokenbytes.hykoreapi.ecs.components.EcsComponent

class EntityTickRefImpl(
    private var index: Int,
    private val archetypeChunk: ArchetypeChunk<EntityStore?>,
    private val store: Store<EntityStore>,
    private val commandBuffer: CommandBuffer<EntityStore?>,
): EntityTickRef {

    fun setIndex(index: Int) {
        this.index = index
    }

    override fun <T : EcsComponent> addComponent(component: Class<T>) {
        store.externalData.world.execute {
            store.addComponent(
                archetypeChunk.getReferenceTo(index),
                ComponentMapper.toComponentType(component)
            )
        }
    }

    override fun <T : EcsComponent> getComponent(type: Class<T>): T? {
        val componentType = ComponentMapper.toComponentType(type)
        val component = store.getComponent(archetypeChunk.getReferenceTo(index), componentType) ?: return null
        @Suppress("UNCHECKED_CAST")
        return ComponentMapper.from(component) as T
    }

    override fun <T : EcsComponent> setComponent(value: T) {
        store.externalData.world.execute {
            store.putComponent(
                archetypeChunk.getReferenceTo(index),
                ComponentMapper.toComponentType(value.javaClass),
                ComponentMapper.toInternal(value)
            )
        }
    }

    override fun <T : EcsComponent> removeComponent(type: Class<T>) {
        store.externalData.world.execute {
            val componentType = ComponentMapper.toComponentType(type)
            store.tryRemoveComponent(archetypeChunk.getReferenceTo(index), componentType)
        }
    }
}