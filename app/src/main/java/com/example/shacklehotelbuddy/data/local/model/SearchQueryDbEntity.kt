package com.example.shacklehotelbuddy.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchQueryDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val checkInDate: String,
    val checkOutDate: String,
    val adultsCount: Int,
    val childrenCount: Int
)