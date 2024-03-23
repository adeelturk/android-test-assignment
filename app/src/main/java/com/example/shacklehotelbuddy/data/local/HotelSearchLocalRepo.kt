package com.example.shacklehotelbuddy.data.local

import com.example.shacklehotelbuddy.data.model.SearchQuery
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HotelSearchLocalRepo @Inject constructor(private val hotelLocalDataSource:HotelSearchLocalDataSource){


    suspend fun saveSearchQuery(searchQuery: SearchQuery){
        hotelLocalDataSource.saveSearchQuery(searchQuery)
    }

    suspend fun getHotelSearchQuery(): Flow<List<SearchQuery>> =
        hotelLocalDataSource.getHotelSearchQuery()


}