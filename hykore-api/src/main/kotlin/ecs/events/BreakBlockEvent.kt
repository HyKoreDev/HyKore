package dev.brokenbytes.hykoreapi.ecs.events

import dev.brokenbytes.hykoreapi.core.Block

interface BreakBlockEvent: EcsEvent {
    val block: Block
    // TODO: Add Item in Hand
    // TODO: Add BlockType
}