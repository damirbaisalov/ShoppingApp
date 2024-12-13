package com.kbtu.dukenapp.presentation.features.home.mvi

import com.kbtu.dukenapp.presentation.model.CategoryUiModel
import com.kbtu.dukenapp.presentation.model.ProductUiModel
import com.kbtu.dukenapp.presentation.mvi.BaseState

data class State(
    val screenState: ScreenState = ScreenState.Loading,
    val isLoading: Boolean = true,
    val categories: List<CategoryUiModel> = emptyList()
) : BaseState()

sealed class ScreenState {
    data object Loading: ScreenState()
    class SuccessLoad(val data: List<ProductUiModel>): ScreenState()
    class ErrorLoad(val message: String): ScreenState()
}