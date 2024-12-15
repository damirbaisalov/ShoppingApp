package com.kbtu.dukenapp.presentation.features.profile.mvi

import com.common.mvi.BaseReducer

class Reducer : BaseReducer<State, Action>() {

    override fun reduce(state: State, action: Action): State {
        return when (action) {
            is Action.SetScreenState -> state.copy(screenState = action.screenState)
            is Action.SetUser -> state.copy(
                screenState = ProfileScreenState.AuthorizedScreen,
                user = action.userUiModel
            )
            is Action.SetOrders -> state.copy(orderHistory = action.orders)
        }
    }
}