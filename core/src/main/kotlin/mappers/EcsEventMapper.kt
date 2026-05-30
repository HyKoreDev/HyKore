package dev.brokenbytes.hykore.mappers

import dev.brokenbytes.hykore.HBreakBlockEvent
import dev.brokenbytes.hykore.HEcsEvent
import dev.brokenbytes.hykore.ecs.events.BreakBlockEventImpl
import dev.brokenbytes.hykoreapi.ecs.events.BreakBlockEvent
import dev.brokenbytes.hykoreapi.ecs.events.EcsEvent

object EcsEventMapper {

    fun from(event: HEcsEvent): EcsEvent = when (event) {
        is HBreakBlockEvent -> BreakBlockEventImpl(BlockMapper.from(event.targetBlock))
        else -> error("Unknown event: $event")
    }

    fun to(eventClass: Class<out EcsEvent>): Class<out HEcsEvent> = when (eventClass) {
        BreakBlockEvent::class.java -> HBreakBlockEvent::class.java
        else -> error("Unknown event class: ${eventClass.name}")
    }
}