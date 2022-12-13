package com.taio.taio.ui.bottomnav

import com.taio.taio.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Home : BottomBarScreen (
        route = "home",
        title = "Home",
        icon = R.drawable.home
    )

    object Submission : BottomBarScreen (
        route = "submission",
        title = "Pengajuan",
        icon = R.drawable.submission
    )

//    object Generate : BottomBarScreen (
//        route   = "generate",
//        title   = "Generate",
//        icon    = Icons.Default.Home
//    )

    object Request : BottomBarScreen (
        route = "request",
        title = "Permintaan",
        icon = R.drawable.request
    )

    object Profile : BottomBarScreen (
        route = "profile",
        title = "Profile",
        icon = R.drawable.profile
    )
}



