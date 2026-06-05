package dev.brokenbytes.hykoreapi.ecs

import dev.brokenbytes.hykoreapi.ecs.components.EcsComponent

interface EntityTickRef {
    fun <T: EcsComponent> addComponent(component: Class<T>)
    fun <T: EcsComponent> getComponent(type: Class<T>): T?
    fun <T: EcsComponent> setComponent(value: T)
    fun <T: EcsComponent> removeComponent(type: Class<T>)
}

inline fun <reified T: EcsComponent> EntityTickRef.addComponent() = addComponent(T::class.java)
inline fun <reified T : EcsComponent> EntityTickRef.getComponent(): T? = getComponent(T::class.java)
inline fun <reified T : EcsComponent> EntityTickRef.removeComponent() = removeComponent(T::class.java)