package dev.brokenbytes.hykoreapi.core

interface Stat {
    val min: Float
    val max: Float
    val current: Float
    val regeneration: Float?
    val modifiers: List<String>
}