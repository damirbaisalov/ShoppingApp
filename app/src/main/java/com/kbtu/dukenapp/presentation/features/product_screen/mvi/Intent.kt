package com.kbtu.dukenapp.presentation.features.product_screen.mvi

import com.common.mvi.BaseIntent

sealed class Intent: BaseIntent() {

    data object OnExitClick: Intent()
    data object OnRefreshClick: Intent()
}