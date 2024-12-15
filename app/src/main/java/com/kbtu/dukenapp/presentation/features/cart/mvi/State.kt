package com.kbtu.dukenapp.presentation.features.cart.mvi

import com.kbtu.dukenapp.presentation.model.ProductUiModel
import com.kbtu.dukenapp.presentation.mvi.BaseState

data class State(
    val cart: List<ProductUiModel> = emptyList()
): BaseState()