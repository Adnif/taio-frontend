package com.taio.taio.ui

import android.view.Surface
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.taio.taio.domain.model.User
import com.taio.taio.ui.screen.HomeScreen
import com.taio.taio.ui.theme.TandatanganioMobileTheme
import com.taio.taio.ui.screen.HomeScreen

enum class TandatanganioScreen() {
    Home,
    Login,
    Register,
    Search,
}

@Composable
fun TandatanganioApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        bottomBar = {}
    ) {
        val navController = rememberNavController()

        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color.White
        ) {

            NavHost(
                navController = navController,
                startDestination = TandatanganioScreen.Home.name,
                modifier = modifier
            ) {
                composable(route = TandatanganioScreen.Home.name) {
                    HomeScreen(User(0, ""))
                }
            }
        }
    }
}

@Composable
fun TandatanganioBottomBar(modifier: Modifier) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = Color.White
    ) {
        
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppScreen() {
    TandatanganioMobileTheme {
        TandatanganioApp(modifier = Modifier.fillMaxSize())
    }
}