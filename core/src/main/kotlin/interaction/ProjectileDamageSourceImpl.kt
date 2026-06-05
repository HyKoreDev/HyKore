package dev.brokenbytes.hykore.interaction

import dev.brokenbytes.hykoreapi.ecs.Entity
import dev.brokenbytes.hykoreapi.interaction.ProjectileDamageSource

class ProjectileDamageSourceImpl(
    override val entity: Entity,
    override val projectile: Entity,
) : ProjectileDamageSource {
}