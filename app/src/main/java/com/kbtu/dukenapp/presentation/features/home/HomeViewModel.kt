package com.kbtu.dukenapp.presentation.features.home

import com.kbtu.dukenapp.presentation.features.home.mvi.Action
import com.kbtu.dukenapp.presentation.features.home.mvi.Effect
import com.kbtu.dukenapp.presentation.features.home.mvi.Intent
import com.kbtu.dukenapp.presentation.features.home.mvi.Reducer
import com.kbtu.dukenapp.presentation.features.home.mvi.State
import com.kbtu.dukenapp.presentation.mvi.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeInteractor: HomeInteractor
) : BaseViewModel<State, Effect, Action, Reducer, HomeInteractor, >(State(), Reducer(), homeInteractor) {

    init {
        viewModelScope.launch {
            publishAction(Action.SetCategories(homeInteractor.loadCategories()))
            publishAction(Action.SetScreenState(homeInteractor.loadScreen()))
        }
    }

    fun performIntent(intent: Intent) {
        when (intent) {
            is Intent.OnRefreshClick -> refreshScreen()
            is Intent.OnAddToCartClick -> onAddToCartClick(intent.productId)
            is Intent.OnCategoryClick -> onCategoryClick(intent.categoryId)
            is Intent.OnProductClick -> onProductClick(intent.productId)
            is Intent.OnProfileClick -> onProfileClick()
            is Intent.OnShoppingCartClick -> onShoppingCartClick()
        }
    }

    private fun onCategoryClick(categoryId: Int) {

    }

    private fun onProductClick(productId: Int) {
        homeInteractor.navigateToProductScreen(productId)
    }

    private fun onAddToCartClick(productId: Int) {

    }

    private fun onProfileClick() {

    }

    private fun onShoppingCartClick() {

    }

    private fun refreshScreen() {
        viewModelScope.launch {
            publishAction(Action.SetLoadingState(true))
            publishAction(Action.SetScreenState(homeInteractor.loadScreen()))
        }
    }
}