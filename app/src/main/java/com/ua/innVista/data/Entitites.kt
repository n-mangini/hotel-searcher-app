package com.ua.innVista.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ua.innVista.hotel.HotelModel

@Entity(tableName = "wishlist")
data class HotelEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val imgUrl: String,
    val location: String,
    val description: String,
    val price: String
)
