package com.example.shacklehotelbuddy.data.remote

import com.example.shacklehotelbuddy.core.networks.requestBlockingFlow
import com.example.shacklehotelbuddy.data.mapper.HotelRequestMapper
import com.example.shacklehotelbuddy.data.model.SearchQuery
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HotelRemoteDataSourceImpl  @Inject constructor(private val hotelDataService:HotelDataService,
                                                     private val hotelRequestMapper: HotelRequestMapper):HotelRemoteDataSource{


  override suspend fun searchHotel(appRequest:SearchQuery){

        hotelDataService.
        searchHotels(hotelRequestMapper.mapToEntity(appRequest)).
        requestBlockingFlow {

        }
    }

}