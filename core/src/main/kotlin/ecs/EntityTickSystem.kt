package dev.brokenbytes.hykore.ecs

import com.hypixel.hytale.component.*
import com.hypixel.hytale.component.query.Query
import com.hypixel.hytale.component.system.tick.EntityTickingSystem
import com.hypixel.hytale.server.core.modules.entity.damage.DamageModule
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore

class EntityTickSystem<T: Component<EntityStore?>>(
    private val componentType: ComponentType<EntityStore?, T?>
): EntityTickingSystem<EntityStore>() {
    override fun tick(
        deltaTime: Float,
        index: Int,
        archetypeChunk: ArchetypeChunk<EntityStore?>,
        store: Store<EntityStore?>,
        commandBuffer: CommandBuffer<EntityStore?>
    ) {
        archetypeChunk.getReferenceTo(index)
    }

    override fun getGroup(): SystemGroup<EntityStore?>? {
        return DamageModule.get().getGatherDamageGroup()
    }

    override fun getQuery(): Query<EntityStore?>? {
        TODO("Not yet implemented")
    }
}