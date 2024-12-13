package com.kbtu.dukenapp.utils

import java.util.EnumMap

class NavigationDependencies {

    @PublishedApi
    internal val dataMap: EnumMap<NavigationDependenciesKeys, Any> = EnumMap(
        NavigationDependenciesKeys::class.java
    )

    fun <D> putDependency(key: NavigationDependenciesKeys, dependency: D) {
        dataMap[key] = dependency
    }

    inline fun <reified D> getDependency(key: NavigationDependenciesKeys): D {
        val rawData = dataMap.getOrDefault(key, null)
        if (rawData is D) return rawData
        else throw IllegalArgumentException("Argument not found")
    }
}