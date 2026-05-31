package dev.brokenbytes.hykoreapi.ecs.components

interface EntityStatMapComponent: EcsComponent {
    val size: Int

    fun getStat(index: Int): Float?
}