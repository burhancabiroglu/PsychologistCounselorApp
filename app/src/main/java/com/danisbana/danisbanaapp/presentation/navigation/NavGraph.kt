package com.danisbana.danisbanaapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.danisbana.danisbanaapp.presentation.screen.auth.forgot.ForgotPasswordRoute
import com.danisbana.danisbanaapp.presentation.screen.auth.login.LoginRoute
import com.danisbana.danisbanaapp.presentation.screen.auth.register.RegisterRoute
import com.danisbana.danisbanaapp.presentation.screen.home.consultant.ConsultantRoute
import com.danisbana.danisbanaapp.presentation.screen.home.root.HomeRoute
import com.danisbana.danisbanaapp.presentation.screen.response.SuccessScreen
import com.danisbana.danisbanaapp.presentation.screen.splash.SplashRoute

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashRoute(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeRoute(navController = navController)
        }
        composable(route = Screen.Login.route) {
            LoginRoute(navController = navController)
        }
        composable(route = Screen.Register.route) {
            RegisterRoute(navController = navController)
        }
        composable(route = Screen.Consultant.route) {
            ConsultantRoute(navController = navController)
        }
        composable(route = Screen.Success.route) {
            SuccessScreen(){
                navController.navigate(Screen.Login.route)
            }
        }
        composable(route = Screen.ForgotPassword.route) {
            ForgotPasswordRoute(navController = navController)
        }
    }
}