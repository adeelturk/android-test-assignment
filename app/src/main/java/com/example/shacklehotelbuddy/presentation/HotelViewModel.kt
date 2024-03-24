package com.example.shacklehotelbuddy.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shacklehotelbuddy.core.networks.error.ErrorEntity
import com.example.shacklehotelbuddy.data.model.Duration
import com.example.shacklehotelbuddy.data.model.SearchQuery
import com.example.shacklehotelbuddy.data.model.hotel.Hotel
import com.example.shacklehotelbuddy.domain.FetchRecentSearchQueriesUseCase
import com.example.shacklehotelbuddy.domain.HotelDetailUseCase
import com.example.shacklehotelbuddy.domain.SaveSearchQueryUseCase
import com.example.shacklehotelbuddy.domain.SearchHotelUseCase
import com.example.shacklehotelbuddy.presentation.ui.HotelAppUiState
import com.example.shacklehotelbuddy.presentation.utils.UiErrorKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(
    private val searchHotelUseCase: SearchHotelUseCase,
    private val hotelDetailUseCase: HotelDetailUseCase,
    private val saveSearchQuery: SaveSearchQueryUseCase,
    private val fetchRecentQueries: FetchRecentSearchQueriesUseCase
) :
    ViewModel() {

    private val _searchQuery = MutableStateFlow(SearchQuery(Duration(), Duration(), 0, 0))
    val hotelsSearchQuery = _searchQuery.asStateFlow()

    private val _progressLoading = MutableStateFlow(false)
    val progressLoading = _progressLoading.asStateFlow()

    private val _appState = MutableStateFlow<HotelAppUiState>(HotelAppUiState.Default)
    val uiAppState = _appState.asStateFlow()

    private val _hotelsList = MutableStateFlow<List<Hotel>>(emptyList())
    val hotelsList = _hotelsList.asStateFlow()

    private val _searchQueryList = MutableStateFlow<List<SearchQuery>>(emptyList())
    val searchQueryList = _searchQueryList.asStateFlow()

    private fun updateSearchQuery(updateBlock: SearchQuery.() -> SearchQuery) {
        _searchQuery.update {
            it.updateBlock()
        }
    }
    init {
        fetchSearchQuery()
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

    fun showLoading(show: Boolean) {
        _progressLoading.value = show
    }

    fun search() {
        if (_searchQuery.value.isValid()) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    saveSearchQuery(_searchQuery.value)
                    showLoading(true)
                    searchHotelUseCase(_searchQuery.value).collect {

                        it.either({
                            showLoading(false)
                            _appState.value = HotelAppUiState.Error(it)
                            it
                        }, {
                            showLoading(false)
                            _hotelsList.value = it
                            _appState.value = HotelAppUiState.HotelSearchDataReceived

                        })
                    }
                }
            }

        } else {
            _appState.value =
                HotelAppUiState.Error(ErrorEntity.FrontEndError(UiErrorKeys.SEARCH_INCOMPLETE_PARAMS))
        }
    }

    private fun fetchSearchQuery() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                fetchRecentQueries().collect {

                    _searchQueryList.value = it
                }
            }
        }
    }

        fun fetchHotelDetail(hotel: Hotel) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    hotelDetailUseCase(hotel).collect {
                        it.either({

                        }, {

                        })
                    }
                }

            }
        }


        fun resetAppUiState() {
            _appState.value = HotelAppUiState.Default
        }
    }