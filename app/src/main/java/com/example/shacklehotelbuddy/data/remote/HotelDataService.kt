package com.example.shacklehotelbuddy.data.remote

import com.example.shacklehotelbuddy.data.model.HotelSearchEntity
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface HotelDataService {

    @POST("properties/v2/list")
   suspend fun searchHotels(@Body data:HotelSearchEntity):Call<JsonElement>

}