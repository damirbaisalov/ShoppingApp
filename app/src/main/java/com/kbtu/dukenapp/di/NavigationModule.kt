package com.kbtu.dukenapp.di

import com.alphicc.brick.RouterConfig
import com.alphicc.brick.TreeRouter
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val navigationModule = module {
    singleOf(::provideRouter)
}

fun provideRouter(): TreeRouter {
    val router: TreeRouter =
        TreeRouter.new(RouterConfig(broadcastFlowReplay = 0, broadcastFlowExtraBufferCapacity = 10))
    return router
}
