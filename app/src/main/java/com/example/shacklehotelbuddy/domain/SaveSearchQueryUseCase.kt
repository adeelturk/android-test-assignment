package com.example.shacklehotelbuddy.domain

import com.example.shacklehotelbuddy.data.HotelRepository
import com.example.shacklehotelbuddy.data.model.SearchQuery
import javax.inject.Inject

class SaveSearchQueryUseCase @Inject constructor(private val hotelRepository: HotelRepository) {

    suspend operator fun  invoke(searchQuery: SearchQuery){
        hotelRepository.saveSearchQuery(searchQuery)
    }
}