package com.taio.taio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.navigation.compose.rememberNavController
import com.taio.taio.ui.TandatanganioApp
import com.taio.taio.ui.TandatanganioScreen
import com.taio.taio.ui.screen.SplashScreen
import com.taio.taio.ui.theme.TandatanganioMobileTheme
import com.taio.taio.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            TandatanganioMobileTheme {
                TandatanganioApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TandatanganioMobileTheme {
        TandatanganioApp()
    }
}