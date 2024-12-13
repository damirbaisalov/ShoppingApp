package com.kbtu.dukenapp.presentation.features.home.implementation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.alphicc.brick.Component
import com.alphicc.brick.TreeRouter
import com.kbtu.dukenapp.domain.repository.OnlineStoreRepository
import com.kbtu.dukenapp.presentation.features.home.HomeViewModel
import com.kbtu.dukenapp.presentation.features.home.ui.HomeScreen
import org.koin.core.context.GlobalContext

val homeComponent by lazy {
    Component(
        key = "HomeScreen",
        onCreate = { _, argument ->
            val dependencies = argument.get<HomeRouterImpl.Args>()
            val treeRouter: TreeRouter = GlobalContext.get().get()
            val router = HomeRouterImpl(dependencies, treeRouter)
            val repository: OnlineStoreRepository = GlobalContext.get().get()
            val interactor = HomeInteractorImpl(
                router = router,
                repository = repository
            )
            HomeViewModel(interactor)
        },
        onDestroy = { viewModelContainer ->
            val viewModel = viewModelContainer.get<HomeViewModel>()
            viewModel.onCleared()
        },
        content = { viewModelContainer, _ ->
            val viewModel = remember(viewModelContainer) { viewModelContainer.get<HomeViewModel>() }
            val state by viewModel.state.collectAsState()
            HomeScreen(state, viewModel::performIntent)
        }
    )
}