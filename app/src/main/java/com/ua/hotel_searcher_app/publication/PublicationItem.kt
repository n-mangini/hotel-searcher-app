package com.ua.hotel_searcher_app.publication

data class PublicationItem(
    val title: String,
    val description: String,
    val location: String
)

val publications = listOf(
    PublicationItem(
        "Cozy Apartment",
        "A beautiful place to stay in the city center.",
        "New York"
    ),
    PublicationItem(
        "Modern Loft",
        "Spacious and bright loft with modern amenities.",
        "Los Angeles"
    ),
    PublicationItem(
        "Beach House",
        "Enjoy the sea breeze at this beachfront property.",
        "Zarate, Argentina"
    )
)