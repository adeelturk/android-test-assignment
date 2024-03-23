package com.example.shacklehotelbuddy.data.remote

import com.example.shacklehotelbuddy.core.functional.Either
import com.example.shacklehotelbuddy.core.networks.error.ErrorEntity
import com.example.shacklehotelbuddy.data.model.SearchQuery
import com.example.shacklehotelbuddy.data.model.hotel.Hotel
import com.example.shacklehotelbuddy.data.model.hotel.HotelDetailRequestEntity
import com.google.gson.JsonElement
import kotlinx.coroutines.flow.Flow

interface HotelRemoteDataSource  {

     suspend fun searchHotel(appRequest:SearchQuery) :  Flow<Either<ErrorEntity, List<Hotel>>>
     suspend fun fetchHotelDetail(hotel:Hotel) :  Flow<Either<ErrorEntity, JsonElement>>

}