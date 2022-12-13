package com.taio.taio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.taio.taio.ui.TandatanganioApp
import com.taio.taio.ui.theme.TandatanganioMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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