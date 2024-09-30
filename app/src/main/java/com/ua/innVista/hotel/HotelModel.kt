package com.ua.innVista.hotel

import com.ua.innVista.data.HotelEntity

data class HotelModel(
    val id: Long,
    val title: String,
    val price: String,
    val location: String,
    val description: String,
    val imgUrl: String
)

fun HotelModel.toEntity(): HotelEntity {
    return HotelEntity(
        id = id,
        title = title,
        price = price,
        location = location,
        description = description,
        imgUrl = imgUrl
    )
}
