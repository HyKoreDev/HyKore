package dev.brokenbytes.hykore.mappers

import dev.brokenbytes.hykoreapi.math.Vector2
import dev.brokenbytes.hykoreapi.math.Vector3
import dev.brokenbytes.hykoreapi.math.Vector3Int
import org.joml.Vector2d
import org.joml.Vector2i
import org.joml.Vector3d
import org.joml.Vector3i

object VectorMapper {

    fun from(vec: Vector2d) = Vector2(vec.x, vec.y)
    fun from(vec: Vector2i) = Vector2i(vec.x, vec.y)
    fun from(vec: Vector3d) = Vector3(vec.x, vec.y, vec.z)
    fun from(vec: Vector3i) = Vector3Int(vec.x, vec.y, vec.z)
}