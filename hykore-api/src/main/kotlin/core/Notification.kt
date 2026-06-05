package dev.brokenbytes.hykoreapi.core

import javax.swing.Icon

interface Notification {
    val title: String
    val description: String
    val icon: String
}

data class NotificationData(
    override val title: String,
    override val description: String,
    override val icon: String,
): Notification {}