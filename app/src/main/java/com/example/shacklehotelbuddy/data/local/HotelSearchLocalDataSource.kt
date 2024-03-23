package com.example.shacklehotelbuddy.data.local

import com.example.shacklehotelbuddy.data.model.SearchQuery
import kotlinx.coroutines.flow.Flow

interface HotelSearchLocalDataSource {

    suspend fun saveSearchQuery(searchQuery: SearchQuery)

    suspend fun getHotelSearchQuery(): Flow<List<SearchQuery>>
}