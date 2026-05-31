package dev.brokenbytes.hykore.ecs.components

import dev.brokenbytes.hykore.HPlayerRef
import dev.brokenbytes.hykoreapi.core.Notification
import dev.brokenbytes.hykoreapi.ecs.components.PlayerRef

class PlayerRefImpl(private val ref: HPlayerRef): PlayerRef {

    override val id: String = ref.uuid.toString()

    override val name: String = ref.username

    override fun sendNotification(notification: Notification) {
    }

    override fun equals(other: Any?) = other is PlayerRefImpl && other.ref === this.ref
    override fun hashCode() = System.identityHashCode(ref)

    companion object {
        fun from(ref: HPlayerRef): PlayerRefImpl {
            return PlayerRefImpl(ref)
        }
    }
}