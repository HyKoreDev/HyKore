package dev.brokenbytes.hykore.ecs.components

import dev.brokenbytes.hykore.HVelocityComponent
import dev.brokenbytes.hykore.mappers.toVector3
import dev.brokenbytes.hykoreapi.ecs.components.VelocityComponent
import dev.brokenbytes.hykoreapi.math.Vector3

class VelocityComponentImpl(private val component: HVelocityComponent) : VelocityComponent {

    override val velocity = component.velocity.toVector3()
    override val speed = velocity.length()
}