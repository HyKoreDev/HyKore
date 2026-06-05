package dev.brokenbytes.hykore.mappers

import com.hypixel.hytale.math.vector.Rotation3f
import dev.brokenbytes.hykoreapi.math.Vector2
import dev.brokenbytes.hykoreapi.math.Vector2Int
import dev.brokenbytes.hykoreapi.math.Vector3
import dev.brokenbytes.hykoreapi.math.Vector3Int
import org.joml.Vector2d
import org.joml.Vector2i
import org.joml.Vector3d
import org.joml.Vector3i

fun Vector2i.toVector() = Vector2Int(x, y)
fun Vector2d.toVector2() = Vector2(x, y)
fun Vector3i.toVector() = Vector3Int(x, y, z)
fun Vector3d.toVector3() = Vector3(x, y, z)

fun Vector2.toHVector(): Vector2d = Vector2d(x, y)

fun Vector3.toHVector(): Vector3d = Vector3d(x, y, z)
fun Vector3.toHRotation(): Rotation3f = Rotation3f(x.toFloat(), y.toFloat(), z.toFloat())
