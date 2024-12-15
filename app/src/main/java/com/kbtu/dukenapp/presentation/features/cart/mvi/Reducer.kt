package com.kbtu.dukenapp.presentation.features.cart.mvi

import com.common.mvi.BaseReducer

class Reducer : BaseReducer<State, Action>() {

    override fun reduce(state: State, action: Action): State =
        when (action) {
            is Action.SetCart -> state.copy(
                cart = action.cart,
                totalPrice = action.cart.sumOf { it.price * it.count })
        }
}