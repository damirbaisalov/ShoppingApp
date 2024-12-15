package com.kbtu.dukenapp.presentation.features.cart

import com.kbtu.dukenapp.presentation.mvi.BaseRouter

interface CartRouter: BaseRouter {

    fun exit()
    fun navigateToProductScreen(productId: Int)
    fun navigateToCheckoutScreen()
}