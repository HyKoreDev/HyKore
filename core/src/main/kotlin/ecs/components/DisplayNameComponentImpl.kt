package dev.brokenbytes.hykore.ecs.components

import dev.brokenbytes.hykore.HDisplayNameComponent
import dev.brokenbytes.hykoreapi.ecs.components.DisplayNameComponent

class DisplayNameComponentImpl(private val component: HDisplayNameComponent): DisplayNameComponent {

    override val displayName = component.displayName?.rawText ?: "UNKNOWN"
}