package com.kbtu.dukenapp.presentation.features.home.implementation

import com.kbtu.dukenapp.data.model.ResponseResult
import com.kbtu.dukenapp.data.model.products.toCartItemDBModel
import com.kbtu.dukenapp.data.model.products.toProductUiModel
import com.kbtu.dukenapp.data.model.products.toUiModel
import com.kbtu.dukenapp.domain.repository.OnlineStoreRepository
import com.kbtu.dukenapp.presentation.features.home.HomeInteractor
import com.kbtu.dukenapp.presentation.features.home.HomeRouter
import com.kbtu.dukenapp.presentation.features.home.mvi.ScreenState
import com.kbtu.dukenapp.presentation.model.CategoryUiModel
import com.kbtu.dukenapp.presentation.model.ProductUiModel
import com.kbtu.dukenapp.presentation.mvi.CoroutineScopeContainer
import kotlinx.coroutines.flow.Flow

class HomeInteractorImpl(
    val router: HomeRouter,
    val repository: OnlineStoreRepository
) : HomeInteractor, CoroutineScopeContainer() {

    override val cartItems: Flow<List<ProductUiModel>> = repository.cartItemsFlow

    override fun navigateToProductScreen(productId: Int) {
        router.navigateToProductScreen(productId)
    }

    override suspend fun loadScreen(): ScreenState {
        return when (val loadedProductsResult = repository.loadProducts()) {
            is ResponseResult.Error -> {
                ScreenState.ErrorLoad(loadedProductsResult.exception.message ?: "An unexpected error occurred.")
            }
            is ResponseResult.Success -> {
                val cartItems = repository.getCartItems().map { it.toProductUiModel() }

                val cartItemsMap = cartItems.associateBy { it.productId }

                val productsWithCart = loadedProductsResult.data.map { product ->
                    product.toUiModel().copy(count = cartItemsMap[product.productId]?.count ?: 0)
                }

                ScreenState.SuccessLoad(productsWithCart)
            }
        }
    }


    override suspend fun loadCategories(): List<CategoryUiModel> {
        return repository.loadCategories().map { it.toUiModel() }
    }

    override suspend fun addProductToCart(product: ProductUiModel) {
        repository.addProductToCart(product.toCartItemDBModel())
    }

    override suspend fun removeProductFromCart(product: ProductUiModel) {
        repository.removeProductFromCart(product.toCartItemDBModel())
    }

    override suspend fun loadCartItems(): List<ProductUiModel> {
        return repository.getCartItems().map { it.toProductUiModel() }
    }
}