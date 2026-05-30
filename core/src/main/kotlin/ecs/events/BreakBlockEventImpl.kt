package dev.brokenbytes.hykore.ecs.events

import dev.brokenbytes.hykoreapi.core.Block
import dev.brokenbytes.hykoreapi.ecs.events.BreakBlockEvent

class BreakBlockEventImpl(override val block: Block) : BreakBlockEvent {

}