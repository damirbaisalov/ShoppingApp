package com.kbtu.dukenapp.presentation.features.product_screen.mvi

import com.common.mvi.BaseReducer

class Reducer : BaseReducer<State, Action>() {

    override fun reduce(state: State, action: Action): State =
        when (action) {
            is Action.SetScreenState -> state.copy(
                screenState = action.screenState,
                isLoading = false
            )

            is Action.SetLoadingState -> state.copy(
                screenState = ScreenState.Loading,
                isLoading = true
            )
        }
}