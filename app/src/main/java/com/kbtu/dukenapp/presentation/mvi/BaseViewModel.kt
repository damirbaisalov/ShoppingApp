package com.kbtu.dukenapp.presentation.mvi

import com.common.mvi.BaseAction
import com.common.mvi.BaseEffect
import com.common.mvi.BaseReducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel<S : BaseState,
        E : BaseEffect,
        A : BaseAction,
        RD : BaseReducer<S, A>,
        I : BaseInteractor<*>
        >
    (
    viewState: S,
    private val reducer: RD,
    protected val interactor: I
) {

    private val coroutineScopeContainer: CoroutineScopeContainer =
        interactor as CoroutineScopeContainer
    private val _sideEffect = MutableSharedFlow<E>(0, 1)
    private val _state = MutableStateFlow(viewState)

    val state: StateFlow<S> = _state
    val sideEffect: SharedFlow<E> = _sideEffect

    private var onClearedCallback: (() -> Unit)? = null
    private var exitHandler: MutableStateFlow<Boolean>? = null
    private var onExit: (() -> Unit)? = null

    private val _stateUpdatesFlow = MutableSharedFlow<A>(
        extraBufferCapacity = Int.MAX_VALUE,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
        replay = 10
    )

    protected val viewModelScope: CoroutineScope = coroutineScopeContainer.coroutineScope

    init {
        launchStateUpdatesFlow()
    }

    fun getExitHandler(): MutableStateFlow<Boolean> = exitHandler!!

    fun getExitAction() = onExit!!

    fun attachUiExitAttributes(exitHandler: MutableStateFlow<Boolean>, onExit: () -> Unit) {
        this.exitHandler = exitHandler
        this.onExit = onExit
    }

    open fun onCleared() {
        onClearedCallback?.invoke()
        coroutineScopeContainer.onCleared()
    }

    protected fun publishAction(action: A) {
        _stateUpdatesFlow.tryEmit(action)
    }

    protected fun publishEffect(newEffect: E) = viewModelScope.launch {
        _sideEffect.emit(newEffect)
    }

    @PublishedApi
    internal fun doOnCleared(onCleared: () -> Unit) {
        this.onClearedCallback = onCleared
    }

    private fun launchStateUpdatesFlow() = _stateUpdatesFlow.onEach { updateAction ->
        withContext(Dispatchers.Default) {
            _state.value = reducer.reduce(_state.value, updateAction)
        }
    }.launchIn(viewModelScope)
}