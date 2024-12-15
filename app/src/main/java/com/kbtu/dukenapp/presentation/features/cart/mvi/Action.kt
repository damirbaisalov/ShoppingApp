package com.kbtu.dukenapp.presentation.features.cart.mvi

import com.common.mvi.BaseAction
import com.kbtu.dukenapp.presentation.model.ProductUiModel

sealed class Action: BaseAction() {

    class SetCart(val cart: List<ProductUiModel>): Action()
}