package com.example.shacklehotelbuddy.data.mapper

import com.example.shacklehotelbuddy.data.local.model.SearchQueryDbEntity
import com.example.shacklehotelbuddy.data.model.Duration
import com.example.shacklehotelbuddy.data.model.SearchQuery
import javax.inject.Inject
import javax.inject.Singleton


class SearchQueryDbMapper @Inject constructor() {

    fun mapFromDomainToDbDto(searchQuery: SearchQuery)= SearchQueryDbEntity(
        checkInDate = searchQuery.checkInDate.toString(),
        checkOutDate = searchQuery.checkoutDate.toString(),
        adultsCount = searchQuery.adultCount,
        childrenCount = searchQuery.childrenCount
        )


    fun mapFromDbToDomainDto(data:SearchQueryDbEntity):SearchQuery{
       val checkInDate= getDate(data.checkInDate)
       val checkOutDate= getDate(data.checkOutDate)
      return  SearchQuery(Duration(checkInDate.first,checkInDate.second,checkInDate.third),
            Duration(checkOutDate.first,checkOutDate.second,checkOutDate.third),
            data.adultsCount,
            data.childrenCount
        )
    }


    fun getDate(formattedDate:String)=
        Triple(formattedDate.split("/")[0].toInt(),formattedDate.split("/")[1].toInt(),formattedDate.split("/")[2].toInt())

}