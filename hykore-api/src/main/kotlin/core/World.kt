package dev.brokenbytes.hykoreapi.core

import dev.brokenbytes.hykoreapi.ecs.components.PlayerRef

interface World {
    val name: String

    fun say(message: Message)
    fun getPlayers(): List<PlayerRef>
}