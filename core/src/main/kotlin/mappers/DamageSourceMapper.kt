package dev.brokenbytes.hykore.mappers

import dev.brokenbytes.hykore.HCommandDamageSource
import dev.brokenbytes.hykore.HDamageSource
import dev.brokenbytes.hykore.HEntityDamageSource
import dev.brokenbytes.hykore.HEnvironmentDamageSource
import dev.brokenbytes.hykore.HProjectileDamageSource
import dev.brokenbytes.hykore.interaction.GenericDamageSourceImpl
import dev.brokenbytes.hykore.interaction.CommandDamageSourceImpl
import dev.brokenbytes.hykore.interaction.EntityDamageSourceImpl
import dev.brokenbytes.hykore.interaction.EnvironmentDamageSourceImpl
import dev.brokenbytes.hykoreapi.interaction.GenericDamageSource
import dev.brokenbytes.hykore.interaction.ProjectileDamageSourceImpl
import dev.brokenbytes.hykoreapi.interaction.DamageSource

object DamageSourceMapper {
    fun from(damage: HDamageSource): DamageSource = when (damage) {
        is GenericDamageSource -> GenericDamageSourceImpl(damage)
        is HProjectileDamageSource -> ProjectileDamageSourceImpl(
            EntityMapper.from(damage.ref),
            EntityMapper.from(damage.projectile)
        )
        is HEntityDamageSource -> EntityDamageSourceImpl(EntityMapper.from(damage.ref))
        is HEnvironmentDamageSource -> EnvironmentDamageSourceImpl()
        is HCommandDamageSource -> CommandDamageSourceImpl()
        else -> throw IllegalArgumentException("Unknown damage source")
    }
}