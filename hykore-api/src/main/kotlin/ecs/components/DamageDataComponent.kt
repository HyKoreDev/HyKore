package dev.brokenbytes.hykoreapi.ecs.components

import kotlin.time.Instant

interface DamageDataComponent: EcsComponent {
    val lastDamageTime: Instant
}

data class DamageDataComponentData(override val lastDamageTime: Instant) : DamageDataComponent