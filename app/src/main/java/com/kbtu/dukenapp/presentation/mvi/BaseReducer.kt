package com.common.mvi

import com.kbtu.dukenapp.presentation.mvi.BaseState

abstract class BaseReducer<S : BaseState, A: BaseAction> {
    abstract fun reduce(state: S, action: A): S
}