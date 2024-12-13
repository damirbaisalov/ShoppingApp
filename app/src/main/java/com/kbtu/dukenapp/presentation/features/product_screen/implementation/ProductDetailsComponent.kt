package com.kbtu.dukenapp.presentation.features.product_screen.implementation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.alphicc.brick.Component
import com.alphicc.brick.TreeRouter
import com.kbtu.dukenapp.domain.repository.OnlineStoreRepository
import com.kbtu.dukenapp.presentation.features.product_screen.ProductDetailsViewModel
import com.kbtu.dukenapp.presentation.features.product_screen.ui.ProductDetailsScreen
import org.koin.core.context.GlobalContext

val productDetailsComponent by lazy {
    Component(
        key = "ProductDetailsScreen",
        onCreate = { _, argument ->
            val dependencies = argument.get<ProductDetailsRouterImpl.Args>()
            val treeRouter: TreeRouter = GlobalContext.get().get()
            val router = ProductDetailsRouterImpl(dependencies, treeRouter)
            val repository: OnlineStoreRepository = GlobalContext.get().get()
            val interactor = ProductDetailsInteractorImpl(
                router = router,
                repository = repository,
                productId = dependencies.productId
            )
            ProductDetailsViewModel(interactor)
        },
        onDestroy = { viewModelContainer ->
            val viewModel = viewModelContainer.get<ProductDetailsViewModel>()
            viewModel.onCleared()
        },
        content = { viewModelContainer, _ ->
            val viewModel = remember(viewModelContainer) { viewModelContainer.get<ProductDetailsViewModel>() }
            val state by viewModel.state.collectAsState()
            ProductDetailsScreen(state, viewModel::performIntent)
        }
    )
}