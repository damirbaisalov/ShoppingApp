package com.kbtu.dukenapp.presentation.features.product_screen.mvi

import com.kbtu.dukenapp.presentation.model.ProductUiModel
import com.kbtu.dukenapp.presentation.mvi.BaseState

data class State(
    val isLoading: Boolean = true,
    val screenState: ScreenState = ScreenState.Loading
): BaseState()

sealed class ScreenState {
    data object Loading: ScreenState()
    class SuccessLoad(val data: ProductUiModel): ScreenState()
    class ErrorLoad(val message: String): ScreenState()
}