package com.kbtu.dukenapp.presentation.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren

abstract class CoroutineScopeContainer {
    val coroutineScope: CoroutineScope =
        CoroutineScope(kotlinx.coroutines.SupervisorJob() + Dispatchers.Default)

    fun onCleared() {
        coroutineScope.coroutineContext.cancelChildren()
    }
}

interface BaseInteractor<R : BaseRouter>