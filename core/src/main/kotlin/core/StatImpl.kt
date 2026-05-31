package dev.brokenbytes.hykore.core

import dev.brokenbytes.hykoreapi.core.Stat

class StatImpl(

    override val min: Float,
    override val max: Float,
    override val current: Float,
    override val regeneration: Float?,
    override val modifiers: List<String>
) : Stat {
}