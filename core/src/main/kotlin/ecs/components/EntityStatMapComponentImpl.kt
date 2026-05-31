package dev.brokenbytes.hykore.ecs.components

import com.hypixel.hytale.server.core.modules.entitystats.asset.DefaultEntityStatTypes
import dev.brokenbytes.hykore.HEntityStatMapComponent
import dev.brokenbytes.hykore.mappers.StatMapper
import dev.brokenbytes.hykoreapi.core.Stat
import dev.brokenbytes.hykoreapi.ecs.components.EntityStatMapComponent

class EntityStatMapComponentImpl(private val component: HEntityStatMapComponent): EntityStatMapComponent {

    override val health: Stat = component.get(DefaultEntityStatTypes.getHealth())?.let {
        StatMapper.from(it)
    } ?: error("EntityStatMapComponent [health] missing from component")
    override val oxygen: Stat = component.get(DefaultEntityStatTypes.getOxygen())?.let {
        StatMapper.from(it)
    } ?: error("EntityStatMapComponent [oxygen] missing from component")
    override val stamina: Stat = component.get(DefaultEntityStatTypes.getStamina())?.let {
        StatMapper.from(it)
    } ?: error("EntityStatMapComponent [stamina] missing from component")
    override val mana: Stat = component.get(DefaultEntityStatTypes.getMana())?.let {
        StatMapper.from(it)
    } ?: error("EntityStatMapComponent [mana] missing from component")
    override val signatureEnergy: Stat = component.get(DefaultEntityStatTypes.getSignatureEnergy())?.let {
        StatMapper.from(it)
    } ?: error("EntityStatMapComponent [signature] missing from component")
    override val ammo: Stat = component.get(DefaultEntityStatTypes.getAmmo())?.let {
        StatMapper.from(it)
    } ?: error("EntityStatMapComponent [ammo] missing from component")
}