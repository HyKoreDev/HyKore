package dev.brokenbytes.hykore.mappers

import com.hypixel.hytale.component.Component
import com.hypixel.hytale.component.ComponentType
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import dev.brokenbytes.hykore.HDeathComponent
import dev.brokenbytes.hykore.HDisplayNameComponent
import dev.brokenbytes.hykore.HPlayerRef
import dev.brokenbytes.hykore.HTransformComponent
import dev.brokenbytes.hykore.HVelocityComponent
import dev.brokenbytes.hykore.ecs.components.DeathComponentImpl
import dev.brokenbytes.hykore.ecs.components.DisplayNameComponentImpl
import dev.brokenbytes.hykore.ecs.components.PlayerRefComponentImpl
import dev.brokenbytes.hykore.ecs.components.TransformComponentImpl
import dev.brokenbytes.hykore.ecs.components.VelocityComponentImpl
import dev.brokenbytes.hykoreapi.ecs.components.EcsComponent
import dev.brokenbytes.hykoreapi.ecs.components.DeathComponent
import dev.brokenbytes.hykoreapi.ecs.components.DisplayNameComponent
import dev.brokenbytes.hykoreapi.ecs.components.PlayerRefComponent
import dev.brokenbytes.hykoreapi.ecs.components.TransformComponent
import dev.brokenbytes.hykoreapi.ecs.components.VelocityComponent

object ComponentMapper {

    fun from(component: Component<EntityStore>): EcsComponent = when (component) {
        is HDeathComponent -> DeathComponentImpl(component)
        is HDisplayNameComponent -> DisplayNameComponentImpl(component)
        is HPlayerRef -> PlayerRefComponentImpl(component)
        is HTransformComponent -> TransformComponentImpl(component)
        is HVelocityComponent -> VelocityComponentImpl(component)
        else -> error("Unknown component: $component")
    }

    @Suppress("UNCHECKED_CAST")
    fun toComponentType(
        componentClass: Class<out EcsComponent>
    ): ComponentType<EntityStore, Component<EntityStore>> = when (componentClass) {
        DeathComponent::class.java -> HDeathComponent.getComponentType()
        DisplayNameComponent::class.java -> HDisplayNameComponent.getComponentType()
        PlayerRefComponent::class.java -> HPlayerRef.getComponentType()
        TransformComponent::class.java -> HTransformComponent.getComponentType()
        VelocityComponent::class.java -> HVelocityComponent.getComponentType()
        else -> error("Unknown component class: ${componentClass.name}")
    } as ComponentType<EntityStore, Component<EntityStore>>
}
