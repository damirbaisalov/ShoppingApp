package com.kbtu.dukenapp.presentation.features.cart


import com.kbtu.dukenapp.presentation.features.cart.mvi.Action
import com.kbtu.dukenapp.presentation.features.cart.mvi.Effect
import com.kbtu.dukenapp.presentation.features.cart.mvi.Intent
import com.kbtu.dukenapp.presentation.features.cart.mvi.Reducer
import com.kbtu.dukenapp.presentation.features.cart.mvi.State
import com.kbtu.dukenapp.presentation.model.ProductUiModel
import com.kbtu.dukenapp.presentation.mvi.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartInteractor: CartInteractor
) : BaseViewModel<State, Effect, Action, Reducer, CartInteractor>(
    State(),
    Reducer(),
    cartInteractor
) {

    init {
        viewModelScope.launch {
            cartInteractor.cartsFlow.onEach {
                publishAction(Action.SetCart(it))
            }.launchIn(viewModelScope)
        }
    }

    fun performIntent(intent: Intent) {
        when (intent) {
            is Intent.OnCheckoutClick -> navigateToCheckout()
            is Intent.OnDecreaseQuantity -> onDecreaseQuantity(intent.product)
            is Intent.OnIncreaseQuantity -> onIncreaseQuantity(intent.product)
            is Intent.OnRemoveFromCartClick -> onRemoveFromCart(intent.product)
            is Intent.OnProductClick -> onProductClick(intent.productId)
        }
    }

    private fun onProductClick(productId: Int) {
        cartInteractor.navigateToProductScreen(productId)
    }

    private fun onIncreaseQuantity(product: ProductUiModel) {
        viewModelScope.launch {
            cartInteractor.addProductToCart((product))
        }
    }

    private fun onDecreaseQuantity(product: ProductUiModel) {
        viewModelScope.launch {
            cartInteractor.removeProductFromCart((product))
        }
    }

    private fun onRemoveFromCart(product: ProductUiModel) {
        viewModelScope.launch {
            cartInteractor.deleteProductFromCart((product))
        }
    }

    private fun navigateToCheckout() {
        interactor.navigateToCheckoutScreen()
    }
}