package com.example.shacklehotelbuddy.data.mapper

import com.example.shacklehotelbuddy.data.mapper.base.RequestDataMapper
import com.example.shacklehotelbuddy.data.model.Child
import com.example.shacklehotelbuddy.data.model.HotelRoom
import com.example.shacklehotelbuddy.data.model.HotelSearchEntity
import com.example.shacklehotelbuddy.data.model.SearchQuery
import dagger.Component.Factory
import javax.inject.Inject

@Factory
class HotelRequestMapper  @Inject constructor() : RequestDataMapper<SearchQuery,HotelSearchEntity>() {

    override fun mapToEntity(appRequest: SearchQuery)= HotelSearchEntity(checkInDate = appRequest.checkInDate, checkOutDate = appRequest.checkoutDate,
            rooms = arrayListOf(HotelRoom(appRequest.adultCount,getChildrenList(appRequest.childrenCount)))
        )
    private fun getChildrenList(childrenCount:Int)=arrayListOf<Child>().apply {
        for (i in 0..childrenCount){

            this.add(Child())
        }
    }
}