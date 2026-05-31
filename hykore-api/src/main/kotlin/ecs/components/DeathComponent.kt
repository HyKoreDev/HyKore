package dev.brokenbytes.hykoreapi.ecs.components

import dev.brokenbytes.hykoreapi.interaction.DamageSource

interface DeathComponent: EcsComponent {
    val damageSource: DamageSource?
}