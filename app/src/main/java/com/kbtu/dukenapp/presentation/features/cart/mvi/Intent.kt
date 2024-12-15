package com.kbtu.dukenapp.presentation.features.cart.mvi

import com.common.mvi.BaseIntent
import com.kbtu.dukenapp.presentation.model.ProductUiModel

sealed class Intent: BaseIntent() {

    class OnIncreaseQuantity(val product: ProductUiModel): Intent()
    class OnDecreaseQuantity(val product: ProductUiModel): Intent()
    class OnRemoveFromCartClick(val product: ProductUiModel): Intent()
    class OnProductClick(val productId: Int): Intent()
    data object OnCheckoutClick: Intent()
}