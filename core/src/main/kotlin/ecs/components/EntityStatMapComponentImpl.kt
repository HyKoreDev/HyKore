package dev.brokenbytes.hykore.ecs.components

import dev.brokenbytes.hykore.HEntityStatMapComponent
import dev.brokenbytes.hykoreapi.ecs.components.EntityStatMapComponent

class EntityStatMapComponentImpl(private val component: HEntityStatMapComponent): EntityStatMapComponent {
    override val size = component.size()
    override fun getStat(index: Int) = component.get(index)?.get()
}