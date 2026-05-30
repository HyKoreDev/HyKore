package dev.brokenbytes.hykore.core

import dev.brokenbytes.hykoreapi.core.Block
import dev.brokenbytes.hykoreapi.core.BlockMaterial
import dev.brokenbytes.hykoreapi.core.BlockType
import dev.brokenbytes.hykoreapi.math.Vector3Int

data class BlockImpl(
    override val position: Vector3Int,
) : Block