package dev.brokenbytes.hykoreapi.math

import kotlin.math.sqrt

class Vector3Int(
    var x: Int = 0,
    var y: Int = 0,
    var z: Int = 0,
) {
    fun length() = sqrt((x * x + y * y + z * z).toDouble())
}
