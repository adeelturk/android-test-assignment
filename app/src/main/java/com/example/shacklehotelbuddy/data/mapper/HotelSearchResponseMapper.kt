package com.example.shacklehotelbuddy.data.mapper

import com.example.shacklehotelbuddy.data.mapper.base.DataMapper
import com.example.shacklehotelbuddy.data.model.hotel.Hotel
import com.example.shacklehotelbuddy.data.model.hotel.Property
import javax.inject.Inject

class HotelSearchResponseMapper @Inject constructor() : DataMapper<Property, Hotel>() {

    override fun mapToDomainModel(serverResponseObj: Property): Hotel {
          return  Hotel(
              propertyId = serverResponseObj.id?:"1",
                hotelImageLink =serverResponseObj.propertyImage?.image?.url ?: "NA",
                hotelName = serverResponseObj.name ?: "NA",
                place = serverResponseObj.neighborhood?.name ?: "NA",
                price = serverResponseObj.price?.options?.firstOrNull()?.formattedDisplayPrice
                    ?: "NA",

                rating =  serverResponseObj.reviews?.run {
                    if ( !score.isNullOrEmpty()) {
                        score
                    }else{
                        "NA"
                    }
                }?:"NA"
            )


    }
}