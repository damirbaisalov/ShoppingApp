package com.kbtu.dukenapp.presentation.features.bottom_menu

import com.alphicc.brick.TreeRouter
import com.kbtu.dukenapp.presentation.features.cart.implementation.CartRouterImpl
import com.kbtu.dukenapp.presentation.features.cart.implementation.cartComponent
import com.kbtu.dukenapp.presentation.features.home.implementation.HomeRouterImpl
import com.kbtu.dukenapp.presentation.features.home.implementation.homeComponent
import com.kbtu.dukenapp.presentation.features.profile.implementation.ProfileRouterImpl
import com.kbtu.dukenapp.presentation.features.profile.implementation.profileComponent
import kotlinx.coroutines.flow.MutableStateFlow

class BottomMenuViewModel(val router: TreeRouter) {

    private val firstMenuRouter =
        router.branch(bottomMenuScreen.key).apply {
            val homeArgument = HomeRouterImpl.Args()
            addComponent(homeComponent, homeArgument)
        }
    private val secondMenuRouter =
        router.branch(bottomMenuScreen.key).apply {
            val cartArgument = CartRouterImpl.Args()
            addComponent(cartComponent, cartArgument)
        }
    private val thirdMenuRouter =
        router.branch(bottomMenuScreen.key).apply {
            val profileArgument = ProfileRouterImpl.Args()
            addComponent(profileComponent, profileArgument)
        }

    val startScreenIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val containerRouter: MutableStateFlow<TreeRouter> = MutableStateFlow(firstMenuRouter)

    fun onFirstMenuClicked() {
        containerRouter.value = firstMenuRouter
        startScreenIndex.value = 0
    }

    fun onSecondMenuClicked() {
        containerRouter.value = secondMenuRouter
        startScreenIndex.value = 1
    }

    fun onThirdMenuClicked() {
        containerRouter.value = thirdMenuRouter
        startScreenIndex.value = 2
    }
}