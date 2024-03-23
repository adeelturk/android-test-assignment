package com.example.shacklehotelbuddy.presentation.ui

import com.example.shacklehotelbuddy.core.networks.error.ErrorEntity

sealed class HotelAppUiState {

    object Default:HotelAppUiState()
    object HotelSearchDataReceived:HotelAppUiState()
    data class Error(val error:ErrorEntity):HotelAppUiState()


}