package dev.brokenbytes.hykore.core

import com.hypixel.hytale.builtin.instances.InstancesPlugin
import com.hypixel.hytale.component.AddReason
import com.hypixel.hytale.math.shape.Box
import com.hypixel.hytale.math.vector.Transform
import com.hypixel.hytale.server.core.asset.type.model.config.Model
import com.hypixel.hytale.server.core.asset.type.model.config.ModelAsset
import com.hypixel.hytale.server.core.entity.UUIDComponent
import com.hypixel.hytale.server.core.modules.entity.component.BoundingBox
import com.hypixel.hytale.server.core.modules.entity.component.Interactable
import com.hypixel.hytale.server.core.modules.entity.component.PersistentModel
import com.hypixel.hytale.server.core.modules.entity.tracker.NetworkId
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import dev.brokenbytes.hykore.HMessage
import dev.brokenbytes.hykore.HModelComponent
import dev.brokenbytes.hykore.HTransformComponent
import dev.brokenbytes.hykore.HWorld
import dev.brokenbytes.hykore.ecs.EntityImpl
import dev.brokenbytes.hykore.ecs.components.PlayerRefComponentImpl
import dev.brokenbytes.hykore.mappers.toHRotation
import dev.brokenbytes.hykore.mappers.toHVector
import dev.brokenbytes.hykore.mappers.toIdentifier
import dev.brokenbytes.hykoreapi.ecs.Entity
import dev.brokenbytes.hykoreapi.core.ModelType
import dev.brokenbytes.hykoreapi.core.Message
import dev.brokenbytes.hykoreapi.core.World
import dev.brokenbytes.hykoreapi.ecs.components.PlayerRefComponent
import dev.brokenbytes.hykoreapi.math.Vector3
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.future.await


class WorldImpl(private val world: HWorld) : World {

    override val name: String = world.name

    override fun say(message: Message) {
        world.logger.atInfo().log("HyKore: Saying: $message")
        val content = HMessage.raw(message.content)

        world.sendMessage(content)
    }

    override fun getPlayers(): List<PlayerRefComponent> =
        world.playerRefs.map { PlayerRefComponentImpl.from(it) }

    override suspend fun spawn(
        name: String,
        model: ModelType,
        position: Vector3,
        rotation: Vector3,
        scale: Float,
        isInteractable: Boolean
    ): Entity {

        val result = CompletableDeferred<Entity>()

        world.execute {
            val holder = EntityStore.REGISTRY.newHolder()

            val transform  = HTransformComponent(position.toHVector(), rotation.toHRotation())

            holder.ensureComponent(UUIDComponent.getComponentType())

            if (isInteractable) {
                holder.ensureComponent(Interactable.getComponentType())
            }

            val modelAsset = ModelAsset.getAssetMap().getAsset(model.toIdentifier())?: error("Could not find model asset")
            val model = Model.createScaledModel(modelAsset, 1.0f)

            holder.addComponent(HModelComponent.getComponentType(), HModelComponent(model))
            holder.addComponent(PersistentModel.getComponentType(), PersistentModel(model.toReference()))
            holder.addComponent(BoundingBox.getComponentType(), BoundingBox(model.boundingBox?: Box()))
            holder.addComponent(HTransformComponent.getComponentType(), transform)
            holder.addComponent(NetworkId.getComponentType(), NetworkId(world.entityStore.takeNextNetworkId()))

            try {
                val entity = world.entityStore.store.addEntity(holder, AddReason.SPAWN)

                if (entity != null) {
                    result.complete(EntityImpl(entity))

                    world.logger.atInfo().log("Entity: $entity has been successfully added. Position: $position, Rotation: $rotation, Scale: $scale, Model: ${model.model}")

                    return@execute
                }

                result.completeExceptionally(IllegalStateException("Entity is null"))
            } catch (t: Throwable) {
                result.completeExceptionally(t)
            }
        }

        return result.await()
    }

    override fun equals(other: Any?) = other is WorldImpl && other.world === this.world
    override fun hashCode() = System.identityHashCode(world)

    companion object {
        fun from(world: HWorld): WorldImpl {
            return WorldImpl(world)
        }
    }
}