package com.kbtu.dukenapp.presentation.features.home

import com.kbtu.dukenapp.presentation.features.home.mvi.Action
import com.kbtu.dukenapp.presentation.features.home.mvi.Effect
import com.kbtu.dukenapp.presentation.features.home.mvi.Intent
import com.kbtu.dukenapp.presentation.features.home.mvi.Reducer
import com.kbtu.dukenapp.presentation.features.home.mvi.State
import com.kbtu.dukenapp.presentation.model.ProductUiModel
import com.kbtu.dukenapp.presentation.mvi.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeInteractor: HomeInteractor
) : BaseViewModel<State, Effect, Action, Reducer, HomeInteractor, >(State(), Reducer(), homeInteractor) {

    init {
        viewModelScope.launch {
            publishAction(Action.SetCategories(homeInteractor.loadCategories()))
            publishAction(Action.SetScreenState(homeInteractor.loadScreen()))
        }

        homeInteractor.cartItems.onEach {
            publishAction(Action.SetCartItems(it))
        }.launchIn(viewModelScope)
    }

    fun performIntent(intent: Intent) {
        when (intent) {
            is Intent.OnRefreshClick -> refreshScreen()
            is Intent.OnAddToCartClick -> onAddToCartClick(intent.product)
            is Intent.OnRemoveFromCartClick -> onRemoveFromCartClick(intent.product)
            is Intent.OnCategoryClick -> onCategoryClick(intent.categoryId)
            is Intent.OnProductClick -> onProductClick(intent.productId)
            is Intent.OnProfileClick -> onProfileClick()
            is Intent.OnShoppingCartClick -> onShoppingCartClick()
        }
    }

    private fun onCategoryClick(categoryId: Int) {
        publishAction(Action.SetCategorySelection(categoryId))
    }

    private fun onProductClick(productId: Int) {
        homeInteractor.navigateToProductScreen(productId)
    }

    private fun onProfileClick() {

    }

    private fun onShoppingCartClick() {

    }

    private fun refreshScreen() {
        viewModelScope.launch {
            publishAction(Action.SetLoadingState(true))
            val screenState = homeInteractor.loadScreen()
            publishAction(Action.SetScreenState(screenState))
            publishAction(Action.SetCategories(homeInteractor.loadCategories()))
        }
    }

    private fun onAddToCartClick(product: ProductUiModel) {
        viewModelScope.launch {
            homeInteractor.addProductToCart(product)
        }
    }

    private fun onRemoveFromCartClick(product: ProductUiModel) {
        viewModelScope.launch {
            homeInteractor.removeProductFromCart(product)
        }
    }
}