package dev.brokenbytes.hykoreapi.core

interface World {
    val name: String

    fun say(message: Message)
    fun getPlayers(): List<PlayerRef>
}