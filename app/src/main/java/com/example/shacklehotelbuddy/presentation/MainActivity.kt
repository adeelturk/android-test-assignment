package com.example.shacklehotelbuddy.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.shacklehotelbuddy.presentation.theme.ShackleHotelBuddyTheme
import com.example.shacklehotelbuddy.presentation.ui.ShackleHotelBuddyApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShackleHotelBuddyTheme {
                val appState = rememberAppState()
                ShackleHotelBuddyApp(appState)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    ShackleHotelBuddyTheme {
        ShackleHotelBuddyApp(rememberAppState())
    }
}