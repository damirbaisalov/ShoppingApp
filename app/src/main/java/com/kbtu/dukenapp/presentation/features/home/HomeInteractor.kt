package com.kbtu.dukenapp.presentation.features.home

import com.kbtu.dukenapp.presentation.features.home.mvi.ScreenState
import com.kbtu.dukenapp.presentation.model.CategoryUiModel
import com.kbtu.dukenapp.presentation.model.ProductUiModel
import com.kbtu.dukenapp.presentation.mvi.BaseInteractor
import kotlinx.coroutines.flow.Flow

interface HomeInteractor : BaseInteractor<HomeRouter> {

    val cartItems: Flow<List<ProductUiModel>>
    fun navigateToProductScreen(productId: Int)

    suspend fun loadScreen(): ScreenState
    suspend fun loadCategories(): List<CategoryUiModel>

    suspend fun addProductToCart(product: ProductUiModel)
    suspend fun removeProductFromCart(product: ProductUiModel)
    suspend fun loadCartItems(): List<ProductUiModel>
}