package com.example.shacklehotelbuddy.di

import com.example.shacklehotelbuddy.data.HotelRepository
import com.example.shacklehotelbuddy.data.HotelRepositoryImpl
import com.example.shacklehotelbuddy.data.local.HotelSearchLocalDataSource
import com.example.shacklehotelbuddy.data.local.HotelSearchLocalDataSourceImpl
import com.example.shacklehotelbuddy.data.local.database.HotelSearchQueryDao
import com.example.shacklehotelbuddy.data.mapper.HotelDetailRequestMapper
import com.example.shacklehotelbuddy.data.mapper.HotelRequestMapper
import com.example.shacklehotelbuddy.data.mapper.HotelSearchResponseMapper
import com.example.shacklehotelbuddy.data.mapper.SearchQueryDbMapper
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
                                      hotelRequestMapper: HotelRequestMapper,
                                      hotelDetailRequestMapper: HotelDetailRequestMapper,
                                      hotelSearchResponseMapper: HotelSearchResponseMapper
    ) :HotelRemoteDataSource{
       return HotelRemoteDataSourceImpl(hotelDataService,hotelRequestMapper,hotelDetailRequestMapper,hotelSearchResponseMapper)
    }

    @Provides
    fun providesHotelSearchLocalDataSource( searchQueryDao: HotelSearchQueryDao,
                                            searchQueryDbMapper: SearchQueryDbMapper
    ):HotelSearchLocalDataSource{

        return HotelSearchLocalDataSourceImpl(searchQueryDao,searchQueryDbMapper)
    }

    @Provides
    fun providesHotelRepository(hotelRemoteDataSource: HotelRemoteDataSource,
                                hotelLocalDataSource: HotelSearchLocalDataSource):HotelRepository{

        return HotelRepositoryImpl(hotelRemoteDataSource,hotelLocalDataSource)
    }

}
