package com.kbtu.dukenapp.presentation.features.cart

import com.kbtu.dukenapp.presentation.model.ProductUiModel
import com.kbtu.dukenapp.presentation.mvi.BaseInteractor
import kotlinx.coroutines.flow.Flow

interface CartInteractor : BaseInteractor<CartRouter> {

    val cartsFlow: Flow<List<ProductUiModel>>

    suspend fun addProductToCart(product: ProductUiModel)
    suspend fun removeProductFromCart(product: ProductUiModel)
    suspend fun deleteProductFromCart(product: ProductUiModel)

    suspend fun createOrder(totalPrice: Double, onSuccess: () -> Unit, onError: (String) -> Unit)

    fun exit()
    fun navigateToProductScreen(productId: Int)
}