package com.kbtu.dukenapp.presentation.features.bottom_menu

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.alphicc.brick.Component
import com.alphicc.brick.TreeRouter
import org.koin.core.context.GlobalContext

@OptIn(ExperimentalAnimationApi::class)
val bottomMenuScreen by lazy {
    Component(
        key = "BottomMenuScreen",
        onCreate = { _, _ ->
            val router: TreeRouter = GlobalContext.get().get()
            BottomMenuViewModel(router)
        },
        content = { dataContainer, _ ->
            val viewModel = dataContainer.get<BottomMenuViewModel>()
            val defaultIndex by viewModel.startScreenIndex.collectAsState()
            val containerRouter by viewModel.containerRouter.collectAsState()

            BottomMenuScreen(
                defaultIndex,
                containerRouter,
                viewModel::onFirstMenuClicked,
                viewModel::onSecondMenuClicked,
                viewModel::onThirdMenuClicked
            )
        }
    )
}