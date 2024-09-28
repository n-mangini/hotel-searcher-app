package com.ua.innVista.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlist")
data class HotelEntity(
    @PrimaryKey (autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val imgUrl: String,
    val location: String,
    val description: String,
    val price: String
)
