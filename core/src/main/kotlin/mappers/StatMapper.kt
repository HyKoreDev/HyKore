package dev.brokenbytes.hykore.mappers

import com.hypixel.hytale.server.core.modules.entitystats.EntityStatValue
import dev.brokenbytes.hykore.HEntityStatMapComponent
import dev.brokenbytes.hykore.core.StatImpl
import dev.brokenbytes.hykoreapi.core.Stat

object StatMapper {
    fun from(stat: EntityStatValue) = StatImpl(
        stat.min,
        stat.max,
        stat.get(),
        stat.regeneratingValues?.fold(0.0f) { acc, value -> acc + value.regenerating.amount },
        modifiers = listOf()
    )
}