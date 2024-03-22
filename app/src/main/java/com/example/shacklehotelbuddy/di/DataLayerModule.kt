package com.example.shacklehotelbuddy.di

import com.example.shacklehotelbuddy.data.mapper.HotelRequestMapper
import com.example.shacklehotelbuddy.data.remote.HotelDataService
import com.example.shacklehotelbuddy.data.remote.HotelRemoteDataSource
import com.example.shacklehotelbuddy.data.remote.HotelRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

// there should be separate modules to make it more readable but i am keeping repo , data sources in one module fo rnow
@Module
@InstallIn(ViewModelComponent::class)
object DataLayerModule {

    @Provides
    fun provideHotelRemoteDataSource( hotelDataService: HotelDataService,
                                      hotelRequestMapper: HotelRequestMapper) :HotelRemoteDataSource{
       return HotelRemoteDataSourceImpl(hotelDataService,hotelRequestMapper)
    }



}


/*

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    fun provideHotelRemoteDataSource(hotelSearchService: HotelSearchService): HotelRemoteDataSource =
        HotelRemoteDataSourceImpl(hotelSearchService)

    @Provides
    fun provideHotelLocalDataSourceImpl(hotelSearchDao: HotelSearchDao): HotelLocalDataSource =
        HotelLocalDataSourceImpl(hotelSearchDao)

    @Provides
    fun provideHotelRepository(
        remoteDataSource: HotelRemoteDataSource,
        localDataSource: HotelLocalDataSource
    ): HotelRepository = HotelRepositoryImpl(remoteDataSource, localDataSource)

}


*/
