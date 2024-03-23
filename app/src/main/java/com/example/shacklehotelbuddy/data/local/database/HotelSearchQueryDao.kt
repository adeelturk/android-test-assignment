package com.example.shacklehotelbuddy.data.local.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.shacklehotelbuddy.data.local.model.SearchQueryDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HotelSearchQueryDao {

    @Upsert
    fun saveHotelSearchQuery(query: SearchQueryDbEntity)

    @Query("SELECT * FROM SearchQueryDbEntity")
    fun getHotelSearchQueries(): Flow<List<SearchQueryDbEntity>>

}