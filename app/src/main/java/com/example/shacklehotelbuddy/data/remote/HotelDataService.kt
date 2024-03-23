package com.example.shacklehotelbuddy.data.remote

import com.example.shacklehotelbuddy.data.model.hotel.HotelDetailRequestEntity
import com.example.shacklehotelbuddy.data.model.hotel.HotelSearchRequestEntity
import com.example.shacklehotelbuddy.data.model.hotel.HotelSearchResponseEntity
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

const val properties="properties/v2/"
interface HotelDataService {

    @POST("${properties}list")
    fun searchHotels(@Body data: HotelSearchRequestEntity):Call<HotelSearchResponseEntity>


    @POST("${properties}detail")
    fun hotelDetail(@Body data: HotelDetailRequestEntity):Call<JsonElement>


}