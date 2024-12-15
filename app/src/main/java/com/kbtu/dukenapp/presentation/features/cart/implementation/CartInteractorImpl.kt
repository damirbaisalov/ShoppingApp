package com.kbtu.dukenapp.presentation.features.cart.implementation

import com.kbtu.dukenapp.data.model.products.toCartItemDBModel
import com.kbtu.dukenapp.domain.repository.AuthTokenRepository
import com.kbtu.dukenapp.domain.repository.OnlineStoreRepository
import com.kbtu.dukenapp.presentation.features.cart.CartInteractor
import com.kbtu.dukenapp.presentation.features.cart.CartRouter
import com.kbtu.dukenapp.presentation.model.ProductUiModel
import com.kbtu.dukenapp.presentation.mvi.CoroutineScopeContainer
import kotlinx.coroutines.flow.Flow

class CartInteractorImpl(
    val router: CartRouter,
    val repository: OnlineStoreRepository,
    private val authTokenRepository: AuthTokenRepository
) : CartInteractor, CoroutineScopeContainer() {

    override val cartsFlow: Flow<List<ProductUiModel>> = repository.cartItemsFlow

    override fun exit() {
        router.exit()
    }

    override fun navigateToProductScreen(productId: Int) {
        router.navigateToProductScreen(productId)
    }

    override suspend fun createOrder(
        totalPrice: Double,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    ) {
        repository.createOrder(authTokenRepository.getUserId(), totalPrice, onSuccess, onError)
    }

    override suspend fun addProductToCart(product: ProductUiModel) {
        repository.addProductToCart(product.toCartItemDBModel())
    }

    override suspend fun removeProductFromCart(product: ProductUiModel) {
        repository.removeProductFromCart(product.toCartItemDBModel())
    }

    override suspend fun deleteProductFromCart(product: ProductUiModel) {
        repository.deleteProductFromCart(product.toCartItemDBModel())
    }
}