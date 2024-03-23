package com.example.shacklehotelbuddy.domain

import com.example.shacklehotelbuddy.data.model.SearchQuery
import com.example.shacklehotelbuddy.data.model.hotel.Hotel
import com.example.shacklehotelbuddy.data.remote.HotelRepository
import javax.inject.Inject

class HotelDetailUseCase @Inject constructor(private val hotelRepository:HotelRepository) {

    suspend operator fun invoke(data: Hotel)=
        hotelRepository.fetchHotelDetail(data)

}