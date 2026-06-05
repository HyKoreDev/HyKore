package dev.brokenbytes.hykoreapi.interaction

import dev.brokenbytes.hykoreapi.ecs.Entity

interface EntityDamageSource: DamageSource {
    val entity: Entity
}