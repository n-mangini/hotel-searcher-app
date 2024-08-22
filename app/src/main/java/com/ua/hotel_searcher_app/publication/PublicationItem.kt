package com.ua.hotel_searcher_app.publication

import com.ua.hotel_searcher_app.R

data class PublicationItem(
    val title: String,
    val description: String,
    val location: String,
    val price: String,
    val imageResId: Int
)

val publications = listOf(
    PublicationItem(
        "Cozy Apartment",
        "A beautiful place to stay in the city center.",
        "New York",
        "$120/night",
        imageResId = R.drawable.image_1
    ),
    PublicationItem(
        "Modern Loft",
        "Spacious and bright loft with modern amenities.",
        "Los Angeles",
        "$200/night",
        imageResId = R.drawable.image_1
    ),
    PublicationItem(
        "Beach House",
        "Enjoy the sea breeze at this beachfront property.",
        "Zarate, Argentina",
        "$1/night",
        imageResId = R.drawable.image_1
    )
)