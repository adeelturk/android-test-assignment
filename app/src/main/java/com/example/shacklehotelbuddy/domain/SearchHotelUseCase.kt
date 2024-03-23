package com.example.shacklehotelbuddy.domain

import com.example.shacklehotelbuddy.data.model.SearchQuery
import com.example.shacklehotelbuddy.data.remote.HotelRepository
import javax.inject.Inject
import javax.inject.Singleton

class SearchHotelUseCase @Inject constructor(private val hotelRepository:HotelRepository) {

    suspend operator fun invoke(data:SearchQuery)=
        hotelRepository.searchHotels(data)

}