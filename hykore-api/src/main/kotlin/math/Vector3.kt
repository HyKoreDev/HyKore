package dev.brokenbytes.hykoreapi.math

import kotlin.math.sqrt

class Vector3(
    val x: Double,
    val y: Double,
    val z: Double
) {
    fun length() = sqrt(x * x + y * y + z * z)
}
