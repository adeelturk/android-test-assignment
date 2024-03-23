package com.example.shacklehotelbuddy.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shacklehotelbuddy.core.utils.ShackleConstants
import com.example.shacklehotelbuddy.presentation.AppState
import com.example.shacklehotelbuddy.presentation.HotelViewModel
import com.example.shacklehotelbuddy.presentation.ui.screens.HotelSearchResultListScreen
import com.example.shacklehotelbuddy.presentation.ui.screens.HotelSearchScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShackleHotelBuddyApp(appState: AppState) {

    val hotelViewModel = hiltViewModel<HotelViewModel>()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = appState.snackbarHostState) },
            containerColor = Color.Transparent,
            contentColor = Color.White
        ) {
            NavHost(
                navController = appState.navController,
                startDestination = ShackleConstants.HomeScreenNav
            ) {

                composable(route = ShackleConstants.HomeScreenNav) {
                    HotelSearchScreen(hotelViewModel,appState) {

                        appState.navController.navigate( ShackleConstants.HotelsSearchResultsScreen)
                    }
                }

                composable(
                    route = ShackleConstants.HotelsSearchResultsScreen
                ) {

                    HotelSearchResultListScreen(hotelViewModel)
                }
            }

        }

    }

}