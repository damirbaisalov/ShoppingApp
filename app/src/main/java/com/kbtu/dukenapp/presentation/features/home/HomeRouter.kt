package com.kbtu.dukenapp.presentation.features.home

import com.kbtu.dukenapp.presentation.mvi.BaseRouter

interface HomeRouter: BaseRouter {

    fun navigateToProductScreen(productId: Int)
}