package dev.brokenbytes.hykore.ecs.components

import dev.brokenbytes.hykore.HDeathComponent
import dev.brokenbytes.hykore.mappers.DamageSourceMapper
import dev.brokenbytes.hykoreapi.ecs.components.DeathComponent
import dev.brokenbytes.hykoreapi.interaction.DamageSource

class DeathComponentImpl(private val deathComponent: HDeathComponent): DeathComponent {
    override val damageSource = deathComponent.deathInfo?.source?.let { info ->
        DamageSourceMapper.from(info)
    }
}