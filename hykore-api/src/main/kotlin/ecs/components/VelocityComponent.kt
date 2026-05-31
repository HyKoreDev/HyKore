package dev.brokenbytes.hykoreapi.ecs.components

import dev.brokenbytes.hykoreapi.math.Vector3

interface VelocityComponent: EcsComponent {
    val velocity: Vector3
    val speed: Double
}