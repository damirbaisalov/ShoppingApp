package com.kbtu.dukenapp.presentation.features.product_screen.mvi

import com.common.mvi.BaseIntent

sealed class Intent: BaseIntent() {

    data object OnExitClick: Intent()
    data object OnRefreshClick: Intent()
    data object OnProfileClick: Intent()
    data object OnShoppingCartClick: Intent()
//    class OnProductClick(val productId: Int): Intent()
    class OnAddToCartClick(val productId: Int): Intent()
//    class OnCategoryClick(val categoryId: Int): Intent()
}