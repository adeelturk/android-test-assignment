package com.example.shacklehotelbuddy.data.remote

import com.example.shacklehotelbuddy.core.functional.Either
import com.example.shacklehotelbuddy.core.networks.error.ErrorEntity
import com.example.shacklehotelbuddy.data.model.SearchQuery
import com.example.shacklehotelbuddy.data.model.hotel.Hotel
import com.example.shacklehotelbuddy.data.model.hotel.HotelDetailRequestEntity
import com.google.gson.JsonElement
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class HotelRepository @Inject constructor(private val hotelRemoteDataSource: HotelRemoteDataSource) {

    suspend fun searchHotels(appRequest: SearchQuery)=
        hotelRemoteDataSource.searchHotel(appRequest)

     suspend fun fetchHotelDetail(hotel: Hotel)=
        hotelRemoteDataSource.fetchHotelDetail(hotel)


}