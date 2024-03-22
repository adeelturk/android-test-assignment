package com.example.shacklehotelbuddy.data.remote

import com.example.shacklehotelbuddy.data.model.SearchQuery

interface HotelRemoteDataSource  {

    abstract suspend fun searchHotel(appRequest:SearchQuery)

}