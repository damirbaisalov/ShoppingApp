package com.kbtu.dukenapp.presentation.features.home

import com.kbtu.dukenapp.domain.model.characters.ProductItem
import com.kbtu.dukenapp.presentation.model.ResourceUiState
import com.kbtu.dukenapp.presentation.mvi.UiEffect
import com.kbtu.dukenapp.presentation.mvi.UiEvent
import com.kbtu.dukenapp.presentation.mvi.UiState

interface HomeContract {
    sealed interface Event : UiEvent

    data class State(
        val productList: ResourceUiState<List<ProductItem>>
    ) : UiState

    sealed interface Effect : UiEffect
}


