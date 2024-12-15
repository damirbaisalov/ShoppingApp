package com.kbtu.dukenapp.presentation.features.profile.ui

import SignInScreen
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.kbtu.dukenapp.data.model.orders.OrderStatus
import com.kbtu.dukenapp.presentation.features.profile.mvi.Effect
import com.kbtu.dukenapp.presentation.features.profile.mvi.Intent
import com.kbtu.dukenapp.presentation.features.profile.mvi.ProfileScreenState
import com.kbtu.dukenapp.presentation.features.profile.mvi.State
import com.kbtu.dukenapp.utils.extension.CollectAsSideEffect
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun ProfileScreen(state: State, performIntent: (Intent) -> Unit, sideEffect: SharedFlow<Effect>) {
    val context = LocalContext.current
    sideEffect.CollectAsSideEffect {
        when (it) {
            is Effect.ShowToast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    when (state.screenState) {
        ProfileScreenState.AuthorizedScreen -> AccountScreen(state, performIntent)
        ProfileScreenState.SignIn -> {
            SignInScreen(
                navigateToSignUp = { performIntent(Intent.NoAccountClick) }
            ) { email, password -> performIntent(Intent.OnSignInClick(email, password)) }
        }
        ProfileScreenState.SignUp -> {
            SignUpScreen(
                navigateToSignIn = { performIntent(Intent.HaveAccountClick) }
            ) { name, email, password -> performIntent(Intent.OnSignUpClick(name, email, password)) }
        }
    }
}
