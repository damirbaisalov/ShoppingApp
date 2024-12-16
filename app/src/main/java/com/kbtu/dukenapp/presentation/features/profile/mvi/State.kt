package com.kbtu.dukenapp.presentation.features.profile.mvi

import com.kbtu.dukenapp.presentation.model.OrderUiModel
import com.kbtu.dukenapp.presentation.model.UserUiModel
import com.kbtu.dukenapp.presentation.mvi.BaseState

data class State(
    val screenState: ProfileScreenState = ProfileScreenState.AuthorizedScreen,
    val user: UserUiModel? = null,
    val orderHistory: List<OrderUiModel> = emptyList()
): BaseState()

sealed class ProfileScreenState {
    data object SignIn: ProfileScreenState()
    data object SignUp: ProfileScreenState()
    data object AuthorizedScreen: ProfileScreenState()
}