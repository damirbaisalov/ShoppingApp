package com.kbtu.dukenapp.presentation.features.product_screen

import com.kbtu.dukenapp.presentation.features.product_screen.mvi.Action
import com.kbtu.dukenapp.presentation.features.product_screen.mvi.Effect
import com.kbtu.dukenapp.presentation.features.product_screen.mvi.Intent
import com.kbtu.dukenapp.presentation.features.product_screen.mvi.Reducer
import com.kbtu.dukenapp.presentation.features.product_screen.mvi.ScreenState
import com.kbtu.dukenapp.presentation.features.product_screen.mvi.State
import com.kbtu.dukenapp.presentation.mvi.BaseViewModel
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val productDetailsInteractor: ProductDetailsInteractor
) : BaseViewModel<State, Effect, Action, Reducer, ProductDetailsInteractor>(
    State(),
    Reducer(),
    productDetailsInteractor
) {

    init {
        viewModelScope.launch {
            publishAction(Action.SetScreenState(ScreenState.SuccessLoad(productDetailsInteractor.loadProductDetails())))
        }
    }

    fun performIntent(intent: Intent) {
        when (intent) {
            is Intent.OnRefreshClick -> refreshScreen()
            is Intent.OnExitClick -> productDetailsInteractor.exit()
        }
    }

    private fun refreshScreen() {
        viewModelScope.launch {
            publishAction(Action.SetLoadingState(true))
            publishAction(Action.SetScreenState(ScreenState.SuccessLoad(productDetailsInteractor.loadProductDetails())))
        }
    }
}