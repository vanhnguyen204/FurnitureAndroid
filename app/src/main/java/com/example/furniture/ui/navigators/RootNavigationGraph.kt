package com.example.furniture.ui.navigators

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.furniture.ui.screens.WelcomeScreen
import com.example.furniture.ui.screens.auth.LoginScreen
import com.example.furniture.utils.NavigationUtils

@Composable
fun NavigationComponent(navController: NavHostController) {


    NavHost(
        navController = navController,
        startDestination = NavigationUtils.welcomeScreen
    ) {
        composable(NavigationUtils.welcomeScreen) {
            WelcomeScreen(navController)
        }
        composable(NavigationUtils.login) {
            LoginScreen(navController)
        }
        composable(NavigationUtils.bottomTab) {
            BottomTab()
        }


    }
}