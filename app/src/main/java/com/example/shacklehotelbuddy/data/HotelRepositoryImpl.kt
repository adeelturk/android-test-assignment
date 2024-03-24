package com.example.shacklehotelbuddy.data

import com.example.shacklehotelbuddy.data.local.HotelSearchLocalDataSource
import com.example.shacklehotelbuddy.data.model.SearchQuery
import com.example.shacklehotelbuddy.data.model.hotel.Hotel
import com.example.shacklehotelbuddy.data.remote.HotelRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HotelRepositoryImpl @Inject constructor(private val hotelRemoteDataSource: HotelRemoteDataSource,
                                              private val hotelLocalDataSource: HotelSearchLocalDataSource):
    HotelRepository {

    override suspend fun searchHotels(appRequest: SearchQuery)=
        hotelRemoteDataSource.searchHotel(appRequest)

    override  suspend fun fetchHotelDetail(hotel: Hotel)=
        hotelRemoteDataSource.fetchHotelDetail(hotel)

    override suspend fun saveSearchQuery(searchQuery: SearchQuery){
        hotelLocalDataSource.saveSearchQuery(searchQuery)
    }

    override suspend fun getHotelSearchQuery(): Flow<List<SearchQuery>> =
        hotelLocalDataSource.getHotelSearchQuery()

}