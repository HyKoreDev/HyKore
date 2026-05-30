package dev.brokenbytes.hykoreapi

import dev.brokenbytes.hykoreapi.core.Notification

interface PluginContext {
    fun registerEcsEventHandler(listener: Any)
    fun registerComponentLifecycleHandler(listener: Any)
}
