package dev.brokenbytes.hykoreapi

interface HyKorePlugin {
    fun setup(ctx: PluginContext)
    fun start(ctx: PluginContext)
    fun teardown()
}