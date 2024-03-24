package com.example.shacklehotelbuddy.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.shacklehotelbuddy.data.local.model.SearchQueryDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HotelSearchQueryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHotelSearchQuery(query: SearchQueryDbEntity)

    @Query("SELECT * FROM SearchQueryDbEntity")
    fun getHotelSearchQueries(): Flow<List<SearchQueryDbEntity>>

}