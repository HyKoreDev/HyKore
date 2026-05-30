package dev.brokenbytes.hykore.mappers

import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import dev.brokenbytes.hykore.core.EntityImpl
import dev.brokenbytes.hykoreapi.core.Entity

object EntityMapper {

    fun from(ref: Ref<EntityStore>): Entity = EntityImpl(ref)
}