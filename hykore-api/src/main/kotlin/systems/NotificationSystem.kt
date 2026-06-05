package dev.brokenbytes.hykoreapi.systems

import dev.brokenbytes.hykoreapi.core.Notification
import dev.brokenbytes.hykoreapi.ecs.components.PlayerRefComponent

interface NotificationSystem {
    fun send(notification: Notification, to: PlayerRefComponent)
}