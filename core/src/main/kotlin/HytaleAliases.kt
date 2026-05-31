package dev.brokenbytes.hykore

import com.hypixel.hytale.builtin.hytalegenerator.props.ManualProp
import com.hypixel.hytale.component.system.EcsEvent
import com.hypixel.hytale.protocol.BlockType
import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.entity.damage.DamageDataComponent
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.event.events.ecs.BreakBlockEvent
import com.hypixel.hytale.server.core.event.events.player.PlayerEvent
import com.hypixel.hytale.server.core.modules.entity.component.DisplayNameComponent
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent
import com.hypixel.hytale.server.core.modules.entity.damage.Damage
import com.hypixel.hytale.server.core.modules.entity.damage.DeathComponent
import com.hypixel.hytale.server.core.modules.physics.component.Velocity
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.Universe
import com.hypixel.hytale.server.core.universe.world.World

typealias HBlock = ManualProp.Block
typealias HBlockType = BlockType

typealias HEcsEvent = EcsEvent
typealias HBreakBlockEvent = BreakBlockEvent
typealias HPlayerDeathEvent = PlayerEvent<out Any>

typealias HMessage = Message

typealias HPlayer = Player
typealias HPlayerRef = PlayerRef

typealias HDamageSource = Damage.Source
typealias HEntityDamageSource = Damage.EntitySource
typealias HProjectileDamageSource = Damage.ProjectileSource
typealias HEnvironmentDamageSource = Damage.EnvironmentSource
typealias HCommandDamageSource = Damage.CommandSource

typealias HDamageDataComponent = DamageDataComponent
typealias HDeathComponent = DeathComponent
typealias HDisplayNameComponent = DisplayNameComponent
typealias HTransformComponent = TransformComponent
typealias HVelocityComponent = Velocity

typealias HUniverse = Universe

typealias HWorld = World