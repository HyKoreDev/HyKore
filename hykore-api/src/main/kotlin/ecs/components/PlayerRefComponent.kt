package dev.brokenbytes.hykoreapi.ecs.components

import dev.brokenbytes.hykoreapi.core.Notification

interface PlayerRefComponent: EcsComponent {
    val id: String
    val name: String
}

data class PlayerRefComponentData(
    override val id: String,
    override val name: String
): PlayerRefComponent