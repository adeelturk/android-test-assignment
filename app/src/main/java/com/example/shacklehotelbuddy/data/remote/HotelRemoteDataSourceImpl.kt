package com.example.shacklehotelbuddy.data.remote

import com.example.shacklehotelbuddy.core.functional.Either
import com.example.shacklehotelbuddy.core.networks.error.ErrorEntity
import com.example.shacklehotelbuddy.core.networks.requestBlocking
import com.example.shacklehotelbuddy.core.networks.requestBlockingFlow
import com.example.shacklehotelbuddy.data.mapper.HotelDetailRequestMapper
import com.example.shacklehotelbuddy.data.mapper.HotelRequestMapper
import com.example.shacklehotelbuddy.data.mapper.HotelSearchResponseMapper
import com.example.shacklehotelbuddy.data.model.SearchQuery
import com.example.shacklehotelbuddy.data.model.hotel.Hotel
import com.example.shacklehotelbuddy.data.model.hotel.HotelDetailRequestEntity
import com.google.gson.JsonElement
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HotelRemoteDataSourceImpl  @Inject constructor(private val hotelDataService:HotelDataService,
                                                     private val hotelRequestMapper: HotelRequestMapper,
                                                     private val hotelDetailRequestMapper: HotelDetailRequestMapper,
                                                     private val hotelSearchResponseMapper:HotelSearchResponseMapper):HotelRemoteDataSource{


  override suspend fun searchHotel(appRequest:SearchQuery)=
        hotelDataService.
        searchHotels(hotelRequestMapper.mapToEntity(appRequest)).
        requestBlockingFlow {
            it.data?.propertySearch?.properties?.run {
                hotelSearchResponseMapper.mapToDomainModel(this)
            }?: arrayListOf()
        }

    override suspend fun fetchHotelDetail(hotel: Hotel): Flow<Either<ErrorEntity, JsonElement>> {
      return  hotelDataService.hotelDetail(hotelDetailRequestMapper.mapToEntity(hotel)).requestBlockingFlow {
          it
      }
    }
}