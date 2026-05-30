package dev.brokenbytes.hykore.mappers

import dev.brokenbytes.hykore.HBlock
import dev.brokenbytes.hykore.core.BlockImpl
import dev.brokenbytes.hykoreapi.core.Block
import org.joml.Vector3i

object BlockMapper {

    fun from(block: HBlock) = BlockImpl(
        Vector3IntMapper.from(block.position)
    )

    fun from(position: Vector3i) = BlockImpl(Vector3IntMapper.from(position))
}