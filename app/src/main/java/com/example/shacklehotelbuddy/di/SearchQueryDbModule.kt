package com.example.shacklehotelbuddy.di

import android.content.Context
import androidx.room.Room
import com.example.shacklehotelbuddy.data.local.database.HotelSearchQueryDao
import com.example.shacklehotelbuddy.data.local.database.HotelSearchQueryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchQueryDbModule {

    @Singleton
    @Provides
    fun provideHotelSearchDatabase(@ApplicationContext context: Context): HotelSearchQueryDatabase =
        Room.databaseBuilder(
            context,
            HotelSearchQueryDatabase::class.java,
            "hotelSearch"
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideHotelSearchDao(database: HotelSearchQueryDatabase): HotelSearchQueryDao =
        database.hotelSearchDao()

}