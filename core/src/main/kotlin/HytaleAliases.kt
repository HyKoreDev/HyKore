package dev.brokenbytes.hykore

import com.hypixel.hytale.builtin.hytalegenerator.props.ManualProp
import com.hypixel.hytale.component.system.EcsEvent
import com.hypixel.hytale.protocol.BlockType
import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.event.events.ecs.BreakBlockEvent
import com.hypixel.hytale.server.core.event.events.player.PlayerEvent
import com.hypixel.hytale.server.core.modules.entity.damage.DeathComponent
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

typealias HDeathComponent = DeathComponent

typealias HUniverse = Universe

typealias HWorld = World