package dev.brokenbytes.hykore.ecs

import com.hypixel.hytale.component.Archetype
import com.hypixel.hytale.component.ArchetypeChunk
import com.hypixel.hytale.component.CommandBuffer
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.component.query.Query
import com.hypixel.hytale.component.system.EntityEventSystem
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import dev.brokenbytes.hykore.HEcsEvent
import dev.brokenbytes.hykore.core.WorldImpl
import dev.brokenbytes.hykoreapi.core.World

class EcsHandlerSystem<E: HEcsEvent>(
    eventClass: Class<E>,
    private val invoke: (World, E) -> Unit
): EntityEventSystem<EntityStore, E>(eventClass) {

    override fun handle(
        index: Int,
        archetypeChunk: ArchetypeChunk<EntityStore?>,
        store: Store<EntityStore?>,
        commandBuffer: CommandBuffer<EntityStore?>,
        event: E
    ) {
        val world = commandBuffer.store.externalData.world
        invoke(WorldImpl.from(world), event)
    }

    override fun getQuery(): Query<EntityStore?>? {
        return Archetype.empty()
    }
}