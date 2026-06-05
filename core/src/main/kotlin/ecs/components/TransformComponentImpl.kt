package dev.brokenbytes.hykore.ecs.components

import dev.brokenbytes.hykore.HTransformComponent
import dev.brokenbytes.hykore.mappers.toVector3
import dev.brokenbytes.hykoreapi.ecs.components.TransformComponent
import dev.brokenbytes.hykoreapi.math.Vector3Int

class TransformComponentImpl(private val component: HTransformComponent): TransformComponent {

    override val position = component.position.toVector3()
}