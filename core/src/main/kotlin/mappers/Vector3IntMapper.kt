package dev.brokenbytes.hykore.mappers

import dev.brokenbytes.hykoreapi.math.Vector3Int
import org.joml.Vector3i

object Vector3IntMapper {

    fun from(vec: Vector3i) = Vector3Int(vec.x, vec.y, vec.z)
}