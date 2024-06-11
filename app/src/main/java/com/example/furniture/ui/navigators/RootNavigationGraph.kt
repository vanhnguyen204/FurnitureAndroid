package com.example.furniture.ui.navigators

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.furniture.constant.Storage
import com.example.furniture.helper.SharedPreferencesHelper
import com.example.furniture.ui.screens.WelcomeScreen
import com.example.furniture.ui.screens.auth.LoginScreen
import com.example.furniture.ui.screens.auth.RegisterScreen
import com.example.furniture.utils.NavigationUtils

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationComponent(navController: NavHostController = rememberNavController()) {

val context = LocalContext.current
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
        composable(NavigationUtils.register) {
            RegisterScreen(navHostController = navController)
        }
        composable(NavigationUtils.bottomTab) {
            BottomTab(signOut = {
                navController.navigate(NavigationUtils.login)
                SharedPreferencesHelper(context).removeDataLocalStorage(Storage.TOKEN.toString())

            })
        }


    }
}