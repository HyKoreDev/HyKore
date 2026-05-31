package dev.brokenbytes.hykore.core

import dev.brokenbytes.hykore.HMessage
import dev.brokenbytes.hykore.HWorld
import dev.brokenbytes.hykore.ecs.components.PlayerRefComponentImpl
import dev.brokenbytes.hykoreapi.core.Message
import dev.brokenbytes.hykoreapi.ecs.components.PlayerRefComponent
import dev.brokenbytes.hykoreapi.core.World

class WorldImpl(private val world: HWorld) : World {

    override val name: String = world.name

    override fun say(message: Message) {
        world.logger.atInfo().log("HyKore: Saying: $message")
        val content = HMessage.raw(message.content)

        world.sendMessage(content)
    }

    override fun getPlayers(): List<PlayerRefComponent> =
        world.playerRefs.map { PlayerRefComponentImpl.from(it) }

    override fun equals(other: Any?) = other is WorldImpl && other.world === this.world
    override fun hashCode() = System.identityHashCode(world)

    companion object {
        fun from(world: HWorld): WorldImpl {
            return WorldImpl(world)
        }
    }
}