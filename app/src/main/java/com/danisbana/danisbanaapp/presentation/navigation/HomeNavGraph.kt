package com.danisbana.danisbanaapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.danisbana.danisbanaapp.presentation.screen.home.dashboard.DashboardRoute
import com.danisbana.danisbanaapp.presentation.screen.home.dashboard.DashboardScreen
import com.danisbana.danisbanaapp.presentation.screen.home.messages.MessagesRoute
import com.danisbana.danisbanaapp.presentation.screen.home.profile.ProfileRoute
import com.danisbana.danisbanaapp.presentation.screen.home.root.HomeActions
import com.danisbana.danisbanaapp.presentation.screen.home.root.HomeViewModel

@Composable
fun SetupHomeNavGraph(navController: NavHostController, homeActions: HomeActions) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route,
    ) {
        composable(route = Screen.Dashboard.route) {
            DashboardRoute(homeActions = homeActions)
        }
        composable(route = Screen.Messages.route) {
            MessagesRoute(homeActions = homeActions)
        }
        composable(route = Screen.Profile.route) {
            ProfileRoute(homeActions = homeActions)
        }
    }
}