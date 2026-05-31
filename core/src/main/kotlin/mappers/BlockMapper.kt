package dev.brokenbytes.hykore.mappers

import dev.brokenbytes.hykore.HBlock
import dev.brokenbytes.hykore.core.BlockImpl
import org.joml.Vector3i

object BlockMapper {

    fun from(block: HBlock) = BlockImpl(
        VectorMapper.from(block.position)
    )

    fun from(position: Vector3i) = BlockImpl(VectorMapper.from(position))
}