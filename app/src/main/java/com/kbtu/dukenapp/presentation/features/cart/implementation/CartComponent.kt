package com.kbtu.dukenapp.presentation.features.cart.implementation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.alphicc.brick.Component
import com.alphicc.brick.TreeRouter
import com.kbtu.dukenapp.domain.repository.AuthTokenRepository
import com.kbtu.dukenapp.domain.repository.OnlineStoreRepository
import com.kbtu.dukenapp.presentation.features.cart.CartViewModel
import com.kbtu.dukenapp.presentation.features.cart.ui.CartScreen
import org.koin.core.context.GlobalContext

val cartComponent by lazy {
    Component(
        key = "CartScreen",
        onCreate = { _, argument ->
            val dependencies = argument.get<CartRouterImpl.Args>()
            val treeRouter: TreeRouter = GlobalContext.get().get()
            val router = CartRouterImpl(dependencies, treeRouter)
            val repository: OnlineStoreRepository = GlobalContext.get().get()
            val authTokenRepository: AuthTokenRepository = GlobalContext.get().get()
            val interactor = CartInteractorImpl(
                router = router,
                repository = repository,
                authTokenRepository = authTokenRepository
            )
            CartViewModel(interactor)
        },
        onDestroy = { viewModelContainer ->
            val viewModel = viewModelContainer.get<CartViewModel>()
            viewModel.onCleared()
        },
        content = { viewModelContainer, _ ->
            val viewModel = remember(viewModelContainer) { viewModelContainer.get<CartViewModel>() }
            val state by viewModel.state.collectAsState()
            CartScreen(state, viewModel::performIntent, viewModel.sideEffect)
        }
    )
}