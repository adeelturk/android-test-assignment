package com.example.shacklehotelbuddy.data.remote

import com.example.shacklehotelbuddy.data.model.SearchQuery
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HotelRepository @Inject constructor(private val hotelRemoteDataSource: HotelRemoteDataSource) {

    suspend fun searchHotels(appRequest: SearchQuery){

        hotelRemoteDataSource.searchHotel(appRequest)
    }
}