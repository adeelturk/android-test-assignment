package com.example.shacklehotelbuddy.domain

import com.example.shacklehotelbuddy.data.HotelRepository
import com.example.shacklehotelbuddy.data.model.SearchQuery
import com.example.shacklehotelbuddy.data.HotelRepositoryImpl
import javax.inject.Inject

class SearchHotelUseCase @Inject constructor(private val hotelRepository: HotelRepository) {

    suspend operator fun invoke(data:SearchQuery)=
        hotelRepository.searchHotels(data)

}