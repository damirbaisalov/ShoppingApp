package com.kbtu.dukenapp.presentation.features.home.implementation

import com.alphicc.brick.TreeRouter
import com.kbtu.dukenapp.presentation.features.home.HomeRouter
import com.kbtu.dukenapp.presentation.features.product_screen.implementation.ProductDetailsRouterImpl
import com.kbtu.dukenapp.presentation.features.product_screen.implementation.productDetailsComponent

class HomeRouterImpl(
    private val args: Args,
    private val router: TreeRouter
) : HomeRouter {

    class Args()

    override fun navigateToProductScreen(productId: Int) {
        val productScreenArgs = ProductDetailsRouterImpl.Args(productId)
        router.addComponent(productDetailsComponent, productScreenArgs)
    }
}