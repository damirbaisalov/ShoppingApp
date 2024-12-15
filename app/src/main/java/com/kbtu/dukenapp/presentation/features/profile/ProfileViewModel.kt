package com.kbtu.dukenapp.presentation.features.profile

import com.kbtu.dukenapp.presentation.features.profile.mvi.Action
import com.kbtu.dukenapp.presentation.features.profile.mvi.Effect
import com.kbtu.dukenapp.presentation.features.profile.mvi.Intent
import com.kbtu.dukenapp.presentation.features.profile.mvi.ProfileScreenState
import com.kbtu.dukenapp.presentation.features.profile.mvi.Reducer
import com.kbtu.dukenapp.presentation.features.profile.mvi.State
import com.kbtu.dukenapp.presentation.mvi.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileInteractor: ProfileInteractor
) : BaseViewModel<State, Effect, Action, Reducer, ProfileInteractor>(
    State(),
    Reducer(),
    profileInteractor
) {

    init {

        viewModelScope.launch {
            publishAction(Action.SetUser(profileInteractor.getCurrentUser()))
            profileInteractor.ordersFlow.onEach {
                publishAction(Action.SetOrders(it))
            }.launchIn(viewModelScope)
        }
    }

    fun performIntent(intent: Intent) {
        when (intent) {
            is Intent.LogoutClick -> onLogoutClick()
            is Intent.OnSignInClick -> onSignInClick(intent.email, intent.password)
            is Intent.HaveAccountClick -> publishAction(Action.SetScreenState(ProfileScreenState.SignIn))
            is Intent.NoAccountClick -> publishAction(Action.SetScreenState(ProfileScreenState.SignUp))
            is Intent.OnSignUpClick -> onSignUpClick(intent.name, intent.email, intent.password)
        }
    }

    private fun onLogoutClick() {
        profileInteractor.logout()
        publishAction(Action.SetScreenState(ProfileScreenState.SignIn))
    }

    private fun onSignInClick(email: String, password: String) {
        viewModelScope.launch {
            profileInteractor.signIn(
                email = email,
                password = password,
                onSuccess = { publishAction(Action.SetUser(it)) },
                onError = { publishEffect(Effect.ShowToast(it)) }
            )
        }
    }

    private fun onSignUpClick(name: String, email: String, password: String) {
        viewModelScope.launch {
            profileInteractor.signUp(
                name = name,
                email = email,
                password = password,
                onSuccess = { publishAction(Action.SetUser(it)) },
                onError = { publishEffect(Effect.ShowToast(it)) }
            )
        }
    }
}