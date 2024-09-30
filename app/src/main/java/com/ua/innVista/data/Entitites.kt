package com.ua.innVista.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ua.innVista.hotel.HotelModel

@Entity(tableName = "hotels")
data class HotelEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val imgUrl: String,
    val location: String,
    val description: String,
    val price: String
)

fun HotelEntity.toModel(): HotelModel {
    return HotelModel(
        id = this.id,
        title = this.title,
        imgUrl = this.imgUrl,
        location = this.location,
        description = this.description,
        price = this.price
    )
}
