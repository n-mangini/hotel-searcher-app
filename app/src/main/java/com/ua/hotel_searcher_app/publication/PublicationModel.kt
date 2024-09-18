package com.ua.hotel_searcher_app.publication

import com.ua.hotel_searcher_app.R

data class PublicationModel(
    val title: String,
    val description: String,
    val location: String,
    val price: String,
    val imageResId: Int = R.drawable.image_1
)
