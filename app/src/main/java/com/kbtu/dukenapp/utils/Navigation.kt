//package com.kbtu.dukenapp.utils
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.kbtu.dukenapp.presentation.features.home.ui.HomeScreen
//import com.kbtu.dukenapp.presentation.features.sign_in.SignInScreen
//import com.kbtu.dukenapp.presentation.features.sign_up.SignUpScreen
//
//@Composable
//fun Navigation() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = Screen.SignIn.route) {
//        composable(Screen.SignIn.route) {
//            SignInScreen(navController = navController)
//        }
//        composable(Screen.SignUp.route) {
//            SignUpScreen(navController = navController)
//        }
//        composable(Screen.Home.route) {
//            HomeScreen(navController = navController)
//        }
//    }
//}