package dev.brokenbytes.hykoreapi.interaction

import dev.brokenbytes.hykoreapi.core.Entity

interface ProjectileDamageSource: EntityDamageSource {
    val projectile: Entity
}