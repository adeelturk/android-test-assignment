package com.example.shacklehotelbuddy.data.local

import com.example.shacklehotelbuddy.data.local.database.HotelSearchQueryDao
import com.example.shacklehotelbuddy.data.mapper.SearchQueryDbMapper
import com.example.shacklehotelbuddy.data.model.SearchQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HotelSearchLocalDataSourceImpl @Inject constructor(
    private val searchQueryDao: HotelSearchQueryDao,
    private val searchQueryDbMapper: SearchQueryDbMapper
) : HotelSearchLocalDataSource {

    override suspend fun saveSearchQuery(searchQuery: SearchQuery) {

        searchQueryDao.saveHotelSearchQuery(searchQueryDbMapper.mapFromDomainToDbDto(searchQuery))
    }

    override suspend fun getHotelSearchQuery(): Flow<List<SearchQuery>> {


        return searchQueryDao.getHotelSearchQueries().map {list->
               list.map {
                    searchQueryDbMapper.mapFromDbToDomainDto(it)
                }
        }
    }
}