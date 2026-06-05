package dev.brokenbytes.hykore.ecs.components

import dev.brokenbytes.hykore.HPlayerRef
import dev.brokenbytes.hykoreapi.core.Notification
import dev.brokenbytes.hykoreapi.ecs.components.PlayerRefComponent

class PlayerRefComponentImpl(private val ref: HPlayerRef): PlayerRefComponent {

    override val id: String = ref.uuid.toString()

    override val name: String = ref.username

    override fun equals(other: Any?) = other is PlayerRefComponentImpl && other.ref === this.ref
    override fun hashCode() = System.identityHashCode(ref)

    companion object {
        fun from(ref: HPlayerRef): PlayerRefComponentImpl {
            return PlayerRefComponentImpl(ref)
        }
    }
}