package com.taio.taio.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.taio.taio.R
import com.taio.taio.domain.model.User
import com.taio.taio.ui.screen.HomeScreen
import com.taio.taio.ui.theme.TandatanganioMobileTheme
import com.taio.taio.ui.theme.fonts

sealed class TandatanganioScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Home : TandatanganioScreen (
        route = "home",
        title = "Home",
        icon = R.drawable.home
    )

    object Submission : TandatanganioScreen (
        route = "submission",
        title = "Pengajuan",
        icon = R.drawable.submission
    )

//    object Generate : TandatanganioScreen (
//        route   = "generate",
//        title   = "Generate",
//        icon    = Icons.Default.Home
//    )

    object Request : TandatanganioScreen (
        route = "request",
        title = "Permintaan",
        icon = R.drawable.request
    )

    object Profile : TandatanganioScreen (
        route = "profile",
        title = "Profile",
        icon = R.drawable.profile
    )
}

@Composable
fun TandatanganioNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = TandatanganioScreen.Home.route
    ) {
        composable(route = TandatanganioScreen.Home.route) {
            HomeScreen(User(0, ""))
        }
        composable(route = TandatanganioScreen.Submission.route) {
            HomeScreen(User(0, ""))
        }
//        composable(route = BottomBarScreen.Generate.route) {
//            // HomeScreen()
//        }
        composable(route = TandatanganioScreen.Request.route) {
            HomeScreen(User(0, ""))
        }
        composable(route = TandatanganioScreen.Profile.route) {
            HomeScreen(User(0, ""))
        }
    }
}

@Composable
fun TandatanganioApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            TandatanganioBottomBar(navController = navController)
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = { TODO() },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.secondary
            ) {
            }
        }
    ) {
        TandatanganioNavGraph(navController = navController)
    }
}

@Composable
fun TandatanganioBottomBar(navController: NavHostController) {
    val screens = listOf(
        TandatanganioScreen.Home,
        TandatanganioScreen.Submission,
//        TandatanganioScreen.Generate,
        TandatanganioScreen.Request,
        TandatanganioScreen.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.secondary,
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth(),
        // backgroundColor = Color.White,
        elevation = 0.dp
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: TandatanganioScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        modifier = Modifier
            .padding(bottom = 5.dp),
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = "Navigate Icon"
            )
        },
        label = {
            Text(
                text = screen.title,
                fontFamily = fonts,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        selectedContentColor = MaterialTheme.colors.primary,
        unselectedContentColor = MaterialTheme.colors.primary.copy(alpha = 0.4f),
        onClick = {
            navController.navigate(screen.route)
        }
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewAppScreen() {
    TandatanganioMobileTheme {
        TandatanganioApp()
    }
}