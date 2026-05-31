package dev.brokenbytes.hykoreapi.ecs.components

import dev.brokenbytes.hykoreapi.core.Notification

interface PlayerRef: EcsComponent {
    val id: String
    val name: String

    fun sendNotification(notification: Notification)
}