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

data class EntityStatMapComponentData(
    override val health: Stat,
    override val oxygen: Stat,
    override val stamina: Stat,
    override val mana: Stat,
    override val signatureEnergy: Stat,
    override val ammo: Stat,
): EntityStatMapComponent