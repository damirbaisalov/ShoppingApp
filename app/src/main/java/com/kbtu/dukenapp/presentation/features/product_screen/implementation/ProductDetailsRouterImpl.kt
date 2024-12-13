package com.kbtu.dukenapp.presentation.features.product_screen.implementation

import com.alphicc.brick.TreeRouter
import com.kbtu.dukenapp.presentation.features.product_screen.ProductDetailsRouter

class ProductDetailsRouterImpl(
    private val args: Args,
    private val router: TreeRouter
) : ProductDetailsRouter {

    class Args(val productId: Int)

    override fun exit() {
        router.backComponent()
    }
}