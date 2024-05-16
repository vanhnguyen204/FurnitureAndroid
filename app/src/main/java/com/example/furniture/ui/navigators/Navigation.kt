package com.example.furniture.ui.navigators

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.furniture.ui.screens.HomeScreen
import com.example.furniture.ui.screens.ProductDetails
import com.example.furniture.ui.screens.WelcomeScreen
import com.example.furniture.ui.screens.auth.LoginScreen
import com.example.furniture.utils.NavigationUtils

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()
    val navigationUtils = NavigationUtils();
    NavHost(navController = navController, startDestination = navigationUtils.welcomeScreen){
        composable(navigationUtils.welcomeScreen) {
            WelcomeScreen(navController)
        }
        composable(navigationUtils.login) {
            LoginScreen(navController)
        }
        composable(navigationUtils.bottomTab){
            BottomTab(navController)
        }
        composable(navigationUtils.productDetails) {
            ProductDetails(navHostController = navController)
        }

    }
}