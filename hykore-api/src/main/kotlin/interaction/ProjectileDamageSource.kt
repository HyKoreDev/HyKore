package dev.brokenbytes.hykoreapi.interaction

import dev.brokenbytes.hykoreapi.ecs.Entity

interface ProjectileDamageSource: EntityDamageSource {
    val projectile: Entity
}