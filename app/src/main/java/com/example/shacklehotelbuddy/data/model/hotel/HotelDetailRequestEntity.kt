package com.example.shacklehotelbuddy.data.model.hotel

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep

data class HotelDetailRequestEntity(
    @SerializedName("currency")
    val currency:String="USD",
    @SerializedName("locale")
    val locale:String= "en_US",
     @SerializedName("siteId")
    val siteId:String= "300000001",
    @SerializedName("eapid")
    val eapid: Int = 1,
    @SerializedName("propertyId")
    val propertyId:String

)
