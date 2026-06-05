package dev.brokenbytes.hykore.mappers

import dev.brokenbytes.hykore.HBlock
import dev.brokenbytes.hykore.core.BlockImpl
import org.joml.Vector3i

object BlockMapper {

    fun from(block: HBlock) = BlockImpl(
        block.position.toVector()
    )

    fun from(position: Vector3i) = BlockImpl(position.toVector())
}