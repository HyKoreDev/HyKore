package dev.brokenbytes.hykore.plugins

import kotlinx.serialization.Serializable

@Serializable
data class HyKoreManifest(
    val id: String,
    val name: String,
    val main: String,
    val version: String,
    val hykoreVersion: String,
    val dependencies: List<String>
)
