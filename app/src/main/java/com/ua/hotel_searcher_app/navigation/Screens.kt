package com.ua.hotel_searcher_app.navigation

enum class Screens {
    Profile,
    Search,
    Whishlist,

    Notifications
}

val basePages = listOf(
    Screens.Profile.name,
    Screens.Search.name,
    Screens.Whishlist.name,
)
