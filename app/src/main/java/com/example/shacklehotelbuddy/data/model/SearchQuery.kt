package com.example.shacklehotelbuddy.data.model

data class SearchQuery(val checkInDate: Duration, val checkoutDate: Duration,
                       val adultCount:Int, val childrenCount:Int){
    
    companion object{
        val defaultSearchQuery= SearchQuery(Duration(), Duration(),0,0)
    }

    // For now i am adding check for default value we can add other basic check as well if needed
    fun isValid()=checkInDate.isValid() && checkoutDate.isValid()&&adultCount>0
}
