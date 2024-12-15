package com.kbtu.dukenapp.presentation.features.profile.implementation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.alphicc.brick.Component
import com.alphicc.brick.TreeRouter
import com.kbtu.dukenapp.domain.repository.AuthTokenRepository
import com.kbtu.dukenapp.domain.repository.UserRepository
import com.kbtu.dukenapp.presentation.features.profile.ProfileViewModel
import com.kbtu.dukenapp.presentation.features.profile.ui.ProfileScreen
import org.koin.core.context.GlobalContext

val profileComponent by lazy {
    Component(
        key = "ProfileScreen",
        onCreate = { _, argument ->
            val dependencies = argument.get<ProfileRouterImpl.Args>()
            val treeRouter: TreeRouter = GlobalContext.get().get()
            val router = ProfileRouterImpl(dependencies, treeRouter)
            val repository: UserRepository = GlobalContext.get().get()
            val authRepository: AuthTokenRepository = GlobalContext.get().get()
            val interactor = ProfileInteractorImpl(
                router = router,
                repository = repository,
                authTokenRepository = authRepository
            )
            ProfileViewModel(interactor)
        },
        onDestroy = { viewModelContainer ->
            val viewModel = viewModelContainer.get<ProfileViewModel>()
            viewModel.onCleared()
        },
        content = { viewModelContainer, _ ->
            val viewModel = remember(viewModelContainer) { viewModelContainer.get<ProfileViewModel>() }
            val state by viewModel.state.collectAsState()
            ProfileScreen(state, viewModel::performIntent, viewModel.sideEffect)
        }
    )
}