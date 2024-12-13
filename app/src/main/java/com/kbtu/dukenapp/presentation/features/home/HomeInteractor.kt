package com.kbtu.dukenapp.presentation.features.home

import com.kbtu.dukenapp.presentation.features.home.mvi.ScreenState
import com.kbtu.dukenapp.presentation.model.CategoryUiModel
import com.kbtu.dukenapp.presentation.mvi.BaseInteractor

interface HomeInteractor: BaseInteractor<HomeRouter> {

    fun navigateToProductScreen(productId: Int)

    suspend fun loadScreen(): ScreenState
    suspend fun loadCategories(): List<CategoryUiModel>
}