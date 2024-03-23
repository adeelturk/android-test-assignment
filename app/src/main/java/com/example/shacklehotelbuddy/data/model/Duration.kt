package com.example.shacklehotelbuddy.data.model

data class Duration(val day:Int=-1, val month:Int=-1, val year:Int=-1){


   override fun toString(): String {
       return  if(day==-1){
           "DD/MM/YYYY"
       }else{
            "$day/$month/$year"
       }

    }

    fun toCachedString():String{
        return  if(day==-1){
            "DD MM/YYYY"
        }else{
            "$day/$month/$year"
        }

    }

    fun isValid()=day!=-1
}
