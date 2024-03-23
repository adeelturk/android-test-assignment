package com.example.shacklehotelbuddy.data.mapper

import com.example.shacklehotelbuddy.data.mapper.base.RequestDataMapper
import com.example.shacklehotelbuddy.data.model.hotel.Child
import com.example.shacklehotelbuddy.data.model.hotel.HotelRoom
import com.example.shacklehotelbuddy.data.model.hotel.HotelSearchRequestEntity
import com.example.shacklehotelbuddy.data.model.SearchQuery
import javax.inject.Inject


class HotelRequestMapper  @Inject constructor() : RequestDataMapper<SearchQuery, HotelSearchRequestEntity>() {

    override fun mapToEntity(appRequest: SearchQuery)= HotelSearchRequestEntity(checkInDate = appRequest.checkInDate, checkOutDate = appRequest.checkoutDate,
            rooms = arrayListOf(HotelRoom(appRequest.adultCount,getChildrenList(appRequest.childrenCount)))
        )
    private fun getChildrenList(childrenCount:Int)=arrayListOf<Child>().apply {
        for (i in 0..childrenCount){

            this.add(Child())
        }
    }
}