package dev.brokenbytes.hykore.mappers

import com.hypixel.hytale.component.Component
import com.hypixel.hytale.component.ComponentType
import com.hypixel.hytale.protocol.FormattedMessage
import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.math.vector.Rotation3f
import com.hypixel.hytale.server.core.asset.type.model.config.Model
import com.hypixel.hytale.server.core.asset.type.model.config.ModelAsset
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import dev.brokenbytes.hykore.*
import dev.brokenbytes.hykore.ecs.components.*
import dev.brokenbytes.hykoreapi.core.ModelType
import dev.brokenbytes.hykoreapi.ecs.components.*
import kotlin.time.toJavaInstant
import kotlin.time.toKotlinInstant


object ComponentMapper {

    fun from(component: Component<EntityStore>): EcsComponent = when (component) {
        is HDamageDataComponent -> DamageDataComponentData(component.lastDamageTime.toKotlinInstant())
        is HDeathComponent -> DeathComponentImpl(component)
        is HEntityStatMapComponent -> EntityStatMapComponentImpl(component)
        is HDisplayNameComponent -> DisplayNameComponentImpl(component)
        is HModelComponent -> ModelComponentData(ModelType.valueOf(component.model.model))
        is HPlayerRef -> PlayerRefComponentImpl(component)
        is HTransformComponent -> TransformComponentImpl(component)
        is HVelocityComponent -> VelocityComponentImpl(component)
        else -> error("Unknown component: $component")
    }

    fun toInternal(component: EcsComponent): Component<EntityStore> = when (component) {
        is ModelComponent -> {
            val modelAsset = ModelAsset.getAssetMap().getAsset(component.model.toIdentifier())?: error("Could not find model asset")
            val model = Model.createScaledModel(modelAsset, 1.0f)

            return HModelComponent(model)
        }
        is DisplayNameComponent -> {
            val message = HMessage(Message.raw(component.displayName).formattedMessage)

            return HDisplayNameComponent(message)
        }
        is DamageDataComponent -> HDamageDataComponent().apply {
            lastDamageTime = component.lastDamageTime.toJavaInstant()
        }
        is TransformComponent -> HTransformComponent(component.position.toHVector(), Rotation3f.ZERO)
        is VelocityComponent -> HVelocityComponent(component.velocity.toHVector())
        is DeathComponent ->
            error("DeathComponent is engine-managed and cannot be written")
        is EntityStatMapComponent ->
            error("EntityStatMapComponent is engine-managed and cannot be written")
        is PlayerRefComponent ->
            error("PlayerRefComponent is engine-managed and cannot be written")

        else -> error("Cannot write component: $component")
    }

    @Suppress("UNCHECKED_CAST")
    fun toComponentType(
        componentClass: Class<out EcsComponent>
    ): ComponentType<EntityStore, Component<EntityStore>> = when {
        ModelComponent::class.java.isAssignableFrom(componentClass) -> HModelComponent.getComponentType()
        DisplayNameComponent::class.java.isAssignableFrom(componentClass) -> HDisplayNameComponent.getComponentType()
        TransformComponent::class.java.isAssignableFrom(componentClass) -> HTransformComponent.getComponentType()
        VelocityComponent::class.java.isAssignableFrom(componentClass) -> HVelocityComponent.getComponentType()
        DeathComponent::class.java.isAssignableFrom(componentClass) -> HDeathComponent.getComponentType()
        EntityStatMapComponent::class.java.isAssignableFrom(componentClass) -> HEntityStatMapComponent.getComponentType()
        PlayerRefComponent::class.java.isAssignableFrom(componentClass) -> HPlayerRef.getComponentType()
        else -> error("Unknown component class: ${componentClass.name}")
    } as ComponentType<EntityStore, Component<EntityStore>>
}
