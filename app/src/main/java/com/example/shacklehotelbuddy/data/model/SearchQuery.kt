package com.example.shacklehotelbuddy.data.model


data class SearchQuery(val checkInDate: Duration, val checkoutDate: Duration,
                       val adultCount:Int, val childrenCount:Int){
    // For now i am adding check for default value we can add other basic check as well if needed
    fun isValid()=checkInDate.isValid() && checkoutDate.isValid()&&adultCount>0

   override fun toString():String{

      return "${checkInDate.toCachedString()} - ${checkoutDate.toCachedString()}  $adultCount adult, $childrenCount children"
    }
}
