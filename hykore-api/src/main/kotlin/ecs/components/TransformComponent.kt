package dev.brokenbytes.hykoreapi.ecs.components

import dev.brokenbytes.hykoreapi.math.Vector3

interface TransformComponent: EcsComponent {
    val position: Vector3
}