package com.example.shacklehotelbuddy.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shacklehotelbuddy.data.local.model.SearchQueryDbEntity

@Database(entities = [SearchQueryDbEntity::class], version = 1)
abstract class HotelSearchQueryDatabase : RoomDatabase() {
    abstract fun hotelSearchDao(): HotelSearchQueryDao
}