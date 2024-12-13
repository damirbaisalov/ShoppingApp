package com.kbtu.dukenapp.presentation.features.product_screen

import com.kbtu.dukenapp.presentation.model.ProductUiModel
import com.kbtu.dukenapp.presentation.mvi.BaseInteractor

interface ProductDetailsInteractor: BaseInteractor<ProductDetailsRouter> {

    fun exit()

    suspend fun loadProductDetails(): ProductUiModel
}