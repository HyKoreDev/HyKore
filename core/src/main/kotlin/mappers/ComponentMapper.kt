package dev.brokenbytes.hykore.mappers

import com.hypixel.hytale.component.Component
import com.hypixel.hytale.component.ComponentType
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import dev.brokenbytes.hykore.HDeathComponent
import dev.brokenbytes.hykoreapi.ecs.components.EcsComponent
import dev.brokenbytes.hykoreapi.ecs.components.DeathComponent

object ComponentMapper {

    fun from(component: Component<EntityStore>): EcsComponent = when (component) {
        is HDeathComponent -> DeathComponent()
        else -> error("Unknown component: $component")
    }

    @Suppress("UNCHECKED_CAST")
    fun toComponentType(
        componentClass: Class<out EcsComponent>
    ): ComponentType<EntityStore, Component<EntityStore>> = when (componentClass) {
        DeathComponent::class.java -> HDeathComponent.getComponentType()
        else -> error("Unknown component class: ${componentClass.name}")
    } as ComponentType<EntityStore, Component<EntityStore>>
}
