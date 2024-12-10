package com.kbtu.dukenapp.presentation.features.sign_up

import com.kbtu.dukenapp.presentation.mvi.UiEffect
import com.kbtu.dukenapp.presentation.mvi.UiEvent
import com.kbtu.dukenapp.presentation.mvi.UiState

interface SigUpContract {
    sealed interface Event : UiEvent

    data object State : UiState

    sealed interface Effect : UiEffect
}


