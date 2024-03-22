package com.example.shacklehotelbuddy.presentation

import androidx.lifecycle.ViewModel
import com.example.shacklehotelbuddy.core.functional.Either
import com.example.shacklehotelbuddy.data.model.Duration
import com.example.shacklehotelbuddy.data.model.SearchQuery
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class HotelViewModel @Inject constructor() : ViewModel() {



    private val _searchQuery = MutableStateFlow(SearchQuery.defaultSearchQuery)
    val hotelsSearchQuery= _searchQuery.asStateFlow()


   private fun updateSearchQuery(updateBlock: SearchQuery.() -> SearchQuery) {
        _searchQuery.update {
            it.updateBlock()
        }
    }

    fun updateCheckInDate(duration: Duration) {
        updateSearchQuery { copy(checkInDate = duration) }
    }

    fun updateCheckoutDate(duration: Duration) {
        updateSearchQuery { copy(checkoutDate = duration) }
    }

    fun updateAdultCount(adultCount: Int) {
        updateSearchQuery { copy(adultCount = adultCount) }
    }

    fun updateChildrenCount(childrenCount: Int) {
        updateSearchQuery { copy(childrenCount = childrenCount) }
    }

    fun search(): Either<Boolean, Boolean> {

       return if(_searchQuery.value.isValid()){
            Either.Right(true)
        }else{

            Either.Left(false)
        }
    }
}