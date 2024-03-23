package com.example.shacklehotelbuddy.data.mapper

import com.example.shacklehotelbuddy.data.mapper.base.RequestDataMapper
import com.example.shacklehotelbuddy.data.model.hotel.Hotel
import com.example.shacklehotelbuddy.data.model.hotel.HotelDetailRequestEntity
import javax.inject.Inject

class HotelDetailRequestMapper @Inject constructor() :
    RequestDataMapper<Hotel, HotelDetailRequestEntity>() {

    override fun mapToEntity(appRequest: Hotel): HotelDetailRequestEntity {

        return HotelDetailRequestEntity(propertyId = appRequest.propertyId)

    }
}