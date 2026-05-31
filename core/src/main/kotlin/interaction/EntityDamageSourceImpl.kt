package dev.brokenbytes.hykore.interaction

import dev.brokenbytes.hykoreapi.core.Entity
import dev.brokenbytes.hykoreapi.interaction.EntityDamageSource

class EntityDamageSourceImpl(
    override val entity: Entity
) : EntityDamageSource {
}