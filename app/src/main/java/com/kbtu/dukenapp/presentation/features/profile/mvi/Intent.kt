package com.kbtu.dukenapp.presentation.features.profile.mvi

import com.common.mvi.BaseIntent

sealed class Intent: BaseIntent() {

    data object LogoutClick: Intent()
    data object NoAccountClick: Intent()
    data object HaveAccountClick: Intent()
    class OnSignInClick(val email: String, val password: String): Intent()
    class OnSignUpClick(val name: String, val email: String, val password: String): Intent()
}