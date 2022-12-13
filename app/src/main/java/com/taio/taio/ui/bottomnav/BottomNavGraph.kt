package com.taio.taio.ui.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.taio.taio.ui.TandatanganioApp
import com.taio.taio.ui.screen.HomeScreen
import com.taio.taio.ui.screen.RequestScreen
import com.taio.taio.ui.screen.SubmissionScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            TandatanganioApp()
        }
        composable(route = BottomBarScreen.Submission.route) {
            TandatanganioApp()
        }
//        composable(route = BottomBarScreen.Generate.route) {
//            // HomeScreen()
//        }
        composable(route = BottomBarScreen.Request.route) {
            TandatanganioApp()
        }
        composable(route = BottomBarScreen.Profile.route) {
            TandatanganioApp()
        }
    }
}