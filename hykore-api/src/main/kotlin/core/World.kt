package dev.brokenbytes.hykoreapi.core

import dev.brokenbytes.hykoreapi.ecs.Entity
import dev.brokenbytes.hykoreapi.ecs.components.PlayerRefComponent
import dev.brokenbytes.hykoreapi.math.Vector3

interface World {
    val name: String

    fun say(message: Message)
    fun getPlayers(): List<PlayerRefComponent>
    suspend fun spawn(
        name: String,
        model: ModelType,
        position: Vector3,
        rotation: Vector3,
        scale: Float,
        isInteractable: Boolean = false,
    ) : Entity
}