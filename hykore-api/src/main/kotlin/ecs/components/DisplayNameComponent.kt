package dev.brokenbytes.hykoreapi.ecs.components

interface DisplayNameComponent: EcsComponent {
    val displayName: String
}

data class DisplayNameComponentData(override val displayName: String) : DisplayNameComponent