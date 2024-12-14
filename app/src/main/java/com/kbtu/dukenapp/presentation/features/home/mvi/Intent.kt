package com.kbtu.dukenapp.presentation.features.home.mvi

import com.common.mvi.BaseIntent
import com.kbtu.dukenapp.presentation.model.ProductUiModel

sealed class Intent: BaseIntent() {

    data object OnRefreshClick: Intent()
    data object OnProfileClick: Intent()
    data object OnShoppingCartClick: Intent()
    class OnProductClick(val productId: Int): Intent()
    class OnAddToCartClick(val product: ProductUiModel): Intent()
    class OnRemoveFromCartClick(val product: ProductUiModel): Intent()
    class OnCategoryClick(val categoryId: Int): Intent()
}