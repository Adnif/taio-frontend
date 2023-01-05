package com.taio.taio.ui

import EditProfileScreen
import EmailCheckScreen
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.taio.taio.R
import com.taio.taio.domain.model.User
import com.taio.taio.ui.screen.*
import com.taio.taio.ui.theme.Gray700
import com.taio.taio.ui.theme.TandatanganioMobileTheme
import com.taio.taio.ui.theme.Typography
import com.taio.taio.viewmodel.SearchViewModel


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

    object Generate : TandatanganioScreen (
        route   = "generate",
        title   = "Generate",
        icon    = R.drawable.qr_code
    )

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

    object Login : TandatanganioScreen (
        route = "login",
        title = "Login",
        icon = R.drawable.profile
    )

    object Register : TandatanganioScreen (
        route = "register",
        title = "Register",
        icon = R.drawable.profile
    )

    object Landing : TandatanganioScreen (
        route = "landing",
        title = "Landing",
        icon = R.drawable.profile
    )
    object Create : TandatanganioScreen(
        route = "create_signature",
        title = "Create Signature",
        icon = R.drawable.generated_count
    )
    object Search : TandatanganioScreen (
        route = "search",
        title = "Search",
        icon = R.drawable.profile
    )
    object OtherProfile : TandatanganioScreen (
        route = "other_profile",
        title = "Other Profile",
        icon = R.drawable.profile
    )
    object EditProfile : TandatanganioScreen (
        route = "edit_profile",
        title = "Edit Profile",
        icon = R.drawable.profile
    )
    object EmailCheck : TandatanganioScreen (
        route = "email_check",
        title = "Email Profile",
        icon = R.drawable.profile
    )
}

@Composable
fun TandatanganioNavGraph(navController: NavHostController) {
    val mockUser = User(R.drawable.avatar, 1, "Asep Konco")
    val user = navController.previousBackStackEntry?.savedStateHandle?.get<User>("profile")

    NavHost(
        navController = navController,
        modifier = Modifier,
        startDestination = TandatanganioScreen.Landing.route
    ) {
        composable(route = TandatanganioScreen.Home.route) {
            HomeScreen( authenticatedUser = mockUser, navController = navController)
        }
        composable(route = TandatanganioScreen.Submission.route) {
            SubmissionScreen()
        }
        composable(route = TandatanganioScreen.Generate.route) {
            ListSignatureScreen(navController = navController)
        }
        composable(route = TandatanganioScreen.Request.route) {
            RequestScreen(navController)
        }
        composable(route = TandatanganioScreen.Profile.route) {
            ProfileScreen(mockUser, navController)
        }
        composable(route = TandatanganioScreen.Login.route) {
            LoginScreen(navController)
        }
        composable(route = TandatanganioScreen.Register.route) {
            RegisterScreen(navController)
        }
        composable(route = TandatanganioScreen.Landing.route) {
            LandingScreen(navController)
        }
        composable(route = TandatanganioScreen.Create.route){
            CreateSignatureScreen(navController = navController)
        }
        composable(route = TandatanganioScreen.Search.route){
            SearchScreen(navController = navController)
        }
        composable(route = TandatanganioScreen.OtherProfile.route){
            if (user != null) {
                OtherProfileScreen(navController = navController, user = user)
            }else{
                OtherProfileScreen(navController = navController, user = mockUser)
            }
        }
        composable(route = TandatanganioScreen.EditProfile.route) {
            EditProfileScreen(mockUser, navController)
        }
        composable(route = TandatanganioScreen.EmailCheck.route) {
            EmailCheckScreen(navController)
        }
    }
}

@Composable
fun TandatanganioApp() {
    val navController = rememberNavController()
    var showBottomBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    showBottomBar = when (navBackStackEntry?.destination?.route){
        "landing" -> false
        "login" -> false
        "register" -> false
        "generate" -> false
        "create_signature" -> false
        "search" -> false
        "other_profile" -> false
        "email_check" -> false
        else -> true
    }

    Scaffold(
        topBar = {
//            TopAppBar() {
//                Text(
//                    text = "Buat Tanda Tangan",
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Medium
//                )
//            }
        },
        bottomBar = {
            if(showBottomBar) TandatanganioBottomBar(navController = navController)
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            if(showBottomBar){
                FloatingActionButton(
                    shape = CircleShape,
                    onClick = { navController.navigate(TandatanganioScreen.Generate.route) },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.secondary,
                    modifier = Modifier.size(70.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = TandatanganioScreen.Generate.icon),
                            contentDescription = "Navigate Icon"
                        )
                        Text(
                            text = TandatanganioScreen.Generate.title,
                            style = Typography.caption,
                        )

                    }
                }
            }
        }
    ) {
        Box(
            Modifier
                .padding(bottom = it.calculateBottomPadding())
                .fillMaxSize()) {
            TandatanganioNavGraph(navController = navController)

        }
    }
}

@Composable
fun TandatanganioBottomBar(navController: NavHostController) {
    val screens = listOf(
        TandatanganioScreen.Home,
        TandatanganioScreen.Submission,
        TandatanganioScreen.Request,
        TandatanganioScreen.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.secondary,
//        backgroundColor = Color.Black,
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth(),
        // backgroundColor = Color.White,
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
                style = Typography.caption
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        selectedContentColor = MaterialTheme.colors.primary,
        unselectedContentColor = Gray700,
        onClick = {
            navController.navigate(screen.route)
        }
    )
    if (screen == TandatanganioScreen.Submission){
        Spacer(Modifier.width(30.dp))
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewAppScreen() {
//    TandatanganioMobileTheme {
//        TandatanganioApp()
//    }
//}