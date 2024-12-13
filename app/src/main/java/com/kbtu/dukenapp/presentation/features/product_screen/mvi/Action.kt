package com.kbtu.dukenapp.presentation.features.product_screen.mvi

import com.common.mvi.BaseAction

sealed class Action: BaseAction() {

    class SetLoadingState(val isLoading: Boolean): Action()
    class SetScreenState(val screenState: ScreenState): Action()
}