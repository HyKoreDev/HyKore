package dev.brokenbytes.hykoreapi.interaction

import dev.brokenbytes.hykoreapi.core.Entity

interface EntityDamageSource: DamageSource {
    val entity: Entity
}