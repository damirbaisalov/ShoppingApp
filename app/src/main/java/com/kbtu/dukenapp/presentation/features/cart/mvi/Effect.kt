package com.kbtu.dukenapp.presentation.features.cart.mvi

import com.common.mvi.BaseEffect

sealed class Effect: BaseEffect() {

    data object ShowSuccessToast: Effect()
    data class ShowErrorToast(val message: String): Effect()
}