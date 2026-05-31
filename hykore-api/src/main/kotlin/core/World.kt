package dev.brokenbytes.hykoreapi.core

import dev.brokenbytes.hykoreapi.ecs.components.PlayerRefComponent

interface World {
    val name: String

    fun say(message: Message)
    fun getPlayers(): List<PlayerRefComponent>
}