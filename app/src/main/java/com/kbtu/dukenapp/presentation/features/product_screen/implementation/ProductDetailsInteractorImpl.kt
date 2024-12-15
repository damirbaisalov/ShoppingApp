package com.kbtu.dukenapp.presentation.features.product_screen.implementation

import com.kbtu.dukenapp.data.model.products.toUiModel
import com.kbtu.dukenapp.domain.repository.OnlineStoreRepository
import com.kbtu.dukenapp.presentation.features.product_screen.ProductDetailsInteractor
import com.kbtu.dukenapp.presentation.features.product_screen.ProductDetailsRouter
import com.kbtu.dukenapp.presentation.model.ProductUiModel
import com.kbtu.dukenapp.presentation.mvi.CoroutineScopeContainer

class ProductDetailsInteractorImpl(
    val router: ProductDetailsRouter,
    val repository: OnlineStoreRepository,
    val productId: Int
) : ProductDetailsInteractor, CoroutineScopeContainer() {

    override fun exit() {
        router.exit()
    }

    override suspend fun loadProductDetails(): ProductUiModel {
        return repository.loadProduct(productId).toUiModel()
    }
}