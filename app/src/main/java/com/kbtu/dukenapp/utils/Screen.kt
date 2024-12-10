package com.kbtu.dukenapp.utils

sealed class Screen(val route: String) {
    data object SignUp : Screen("nav_sign_up")
    data object SignIn : Screen("nav_sign_in")
    data object Home : Screen("nav_home")

    companion object {
        const val CITY_ITEM = "cityItem"
    }
}