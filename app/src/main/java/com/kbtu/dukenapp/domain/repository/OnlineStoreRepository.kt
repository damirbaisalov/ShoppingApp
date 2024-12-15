package com.kbtu.dukenapp.domain.repository

import com.kbtu.dukenapp.data.model.ResponseResult
import com.kbtu.dukenapp.data.model.products.CartItemDBModel
import com.kbtu.dukenapp.data.model.products.CategoryApiModel
import com.kbtu.dukenapp.data.model.products.ProductItemApiModel
import com.kbtu.dukenapp.presentation.model.ProductUiModel
import kotlinx.coroutines.flow.Flow

interface OnlineStoreRepository {

    val cartItemsFlow: Flow<List<ProductUiModel>>

    suspend fun loadProducts(): ResponseResult<List<ProductItemApiModel>>
    suspend fun loadCategories(): List<CategoryApiModel>
    suspend fun loadProduct(id: Int): ProductItemApiModel

    suspend fun addProductToCart(cartItem: CartItemDBModel)
    suspend fun removeProductFromCart(cartItem: CartItemDBModel)
    suspend fun deleteProductFromCart(cartItem: CartItemDBModel)
    suspend fun getCartItems(): List<CartItemDBModel>

    suspend fun createOrder(
        userId: Int,
        totalPrice: Double,
        successResult: () -> Unit,
        errorResult: (String) -> Unit
    )
}