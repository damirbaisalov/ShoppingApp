package com.kbtu.dukenapp.presentation.features.cart.implementation

import com.alphicc.brick.TreeRouter
import com.kbtu.dukenapp.presentation.features.cart.CartRouter
import com.kbtu.dukenapp.presentation.features.product_screen.implementation.ProductDetailsRouterImpl
import com.kbtu.dukenapp.presentation.features.product_screen.implementation.productDetailsComponent

class CartRouterImpl(
    private val args: Args,
    private val router: TreeRouter
) : CartRouter {

    class Args()

    override fun exit() {
        router.backComponent()
    }

    override fun navigateToProductScreen(productId: Int) {
        val productScreenArgs = ProductDetailsRouterImpl.Args(productId)
        router.addComponent(productDetailsComponent, productScreenArgs)
    }

    override fun navigateToCheckoutScreen() {
//        val checkoutScreenArgs = ProductDetailsRouterImpl.Args()
//        router.addComponent()
    }
}