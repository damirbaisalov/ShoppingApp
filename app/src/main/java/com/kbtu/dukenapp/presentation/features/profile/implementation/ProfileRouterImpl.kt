package com.kbtu.dukenapp.presentation.features.profile.implementation

import com.alphicc.brick.TreeRouter
import com.kbtu.dukenapp.presentation.features.cart.CartRouter
import com.kbtu.dukenapp.presentation.features.product_screen.implementation.ProductDetailsRouterImpl
import com.kbtu.dukenapp.presentation.features.product_screen.implementation.productDetailsComponent
import com.kbtu.dukenapp.presentation.features.profile.ProfileRouter

class ProfileRouterImpl(
    private val args: Args,
    private val router: TreeRouter
) : ProfileRouter {

    class Args()

//    override fun exit() {
//        router.backComponent()
//    }

//    override fun navigateToProductScreen(productId: Int) {
//        val productScreenArgs = ProductDetailsRouterImpl.Args(productId)
//        router.addComponent(productDetailsComponent, productScreenArgs)
//    }
//
//    override fun navigateToCheckoutScreen() {
////        val checkoutScreenArgs = ProductDetailsRouterImpl.Args()
////        router.addComponent()
//    }
}