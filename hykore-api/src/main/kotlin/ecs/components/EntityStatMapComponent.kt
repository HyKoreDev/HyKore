package dev.brokenbytes.hykoreapi.ecs.components

import dev.brokenbytes.hykoreapi.core.Stat

interface EntityStatMapComponent: EcsComponent {

    val health: Stat
    val oxygen: Stat
    val stamina: Stat
    val mana: Stat
    val signatureEnergy: Stat
    val ammo: Stat
}