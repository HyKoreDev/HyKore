package dev.brokenbytes.hykoreapi.ecs.components

import dev.brokenbytes.hykoreapi.core.ModelType

sealed interface ModelComponent : EcsComponent {
    val model: ModelType
}

data class ModelComponentData(override val model: ModelType): ModelComponent
