package dev.brokenbytes.hykoreapi.core

interface PlayerRef {
    val id: String
    val name: String

    fun sendNotification(notification: Notification)
}