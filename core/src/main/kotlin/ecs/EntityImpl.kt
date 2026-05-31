package dev.brokenbytes.hykore.ecs

import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import dev.brokenbytes.hykore.mappers.ComponentMapper
import dev.brokenbytes.hykoreapi.core.Entity
import dev.brokenbytes.hykoreapi.core.getComponent
import dev.brokenbytes.hykoreapi.ecs.components.DisplayNameComponent
import dev.brokenbytes.hykoreapi.ecs.components.EcsComponent

class EntityImpl(private val ref: Ref<EntityStore>): Entity {

    override fun <T : EcsComponent> getComponent(type: Class<T>): T? {
        val componentType = ComponentMapper.toComponentType(type)
        val component = ref.store.getComponent(ref, componentType) ?: return null
        @Suppress("UNCHECKED_CAST")
        return ComponentMapper.from(component) as T
    }

    override fun <T : EcsComponent> setComponent(value: T) {
    }

    override fun <T : EcsComponent> removeComponent(type: Class<T>) {
        val componentType = ComponentMapper.toComponentType(type)
        ref.store.tryRemoveComponent(ref, componentType)
    }

    override fun toString() = "Entity[${this.getComponent<DisplayNameComponent>()?.displayName ?: "unknown"}"
}