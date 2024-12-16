package com.kbtu.dukenapp.presentation.features.profile.mvi

import com.common.mvi.BaseReducer

class Reducer : BaseReducer<State, Action>() {

    override fun reduce(state: State, action: Action): State {
        return when (action) {
            is Action.SetScreenState -> state.copy(screenState = action.screenState)
            is Action.SetUser -> setUser(state, action)
            is Action.SetOrders -> state.copy(orderHistory = action.orders)
        }
    }

    private fun setUser(state: State, action: Action.SetUser): State {
        val screenState =
            if (action.userUiModel == null) ProfileScreenState.SignIn else ProfileScreenState.AuthorizedScreen
        return state.copy(
            screenState = screenState,
            user = action.userUiModel
        )
    }
}