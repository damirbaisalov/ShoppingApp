package com.kbtu.dukenapp.presentation.features.profile.mvi

import com.common.mvi.BaseAction
import com.common.mvi.BaseEffect

sealed class Effect: BaseEffect() {

    class ShowToast(val message: String): Effect()
}