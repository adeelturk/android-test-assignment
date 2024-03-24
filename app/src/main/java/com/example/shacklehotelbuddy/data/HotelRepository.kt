package com.example.shacklehotelbuddy.data

import com.example.shacklehotelbuddy.core.functional.Either
import com.example.shacklehotelbuddy.core.networks.error.ErrorEntity
import com.example.shacklehotelbuddy.data.model.SearchQuery
import com.example.shacklehotelbuddy.data.model.hotel.Hotel
import com.google.gson.JsonElement
import kotlinx.coroutines.flow.Flow

interface HotelRepository{
    suspend fun searchHotels(appRequest: SearchQuery):Flow<Either<ErrorEntity, List<Hotel>>>
    suspend fun fetchHotelDetail(hotel: Hotel): Flow<Either<ErrorEntity, JsonElement>>
    suspend fun saveSearchQuery(searchQuery: SearchQuery)
    suspend fun getHotelSearchQuery(): Flow<List<SearchQuery>>

}