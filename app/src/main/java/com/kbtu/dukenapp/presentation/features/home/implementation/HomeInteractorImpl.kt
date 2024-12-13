package com.kbtu.dukenapp.presentation.features.home.implementation

import com.kbtu.dukenapp.data.model.ResponseResult
import com.kbtu.dukenapp.data.model.products.toUiModel
import com.kbtu.dukenapp.domain.repository.OnlineStoreRepository
import com.kbtu.dukenapp.presentation.features.home.HomeInteractor
import com.kbtu.dukenapp.presentation.features.home.HomeRouter
import com.kbtu.dukenapp.presentation.features.home.mvi.ScreenState
import com.kbtu.dukenapp.presentation.model.CategoryUiModel
import com.kbtu.dukenapp.presentation.mvi.CoroutineScopeContainer

class HomeInteractorImpl(
    val router: HomeRouter,
    val repository: OnlineStoreRepository
) : HomeInteractor, CoroutineScopeContainer() {

    override fun navigateToProductScreen(productId: Int) {
        router.navigateToProductScreen(productId)
    }

    override suspend fun loadScreen(): ScreenState {
        return when (val loadedProductsResult = repository.loadProducts()) {
            is ResponseResult.Error -> ScreenState.ErrorLoad(loadedProductsResult.exception.message.orEmpty())
            is ResponseResult.Success -> ScreenState.SuccessLoad(loadedProductsResult.data.map { it.toUiModel() })
        }
    }

    override suspend fun loadCategories(): List<CategoryUiModel> {
        return repository.loadCategories().map { it.toUiModel() }
    }
}