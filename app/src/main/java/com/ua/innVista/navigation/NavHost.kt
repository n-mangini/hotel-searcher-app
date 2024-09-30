package com.ua.innVista.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ua.innVista.notifications.Notifications
import com.ua.innVista.profile.Profile
import com.ua.innVista.hotel.HotelSearch
import com.ua.innVista.wishlist.Wishlist

@Composable
fun NavHostComposable(innerPadding: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Search.name,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 10.dp)
    ) {
        composable(route = Screens.Notifications.name) {
            Notifications()
        }
        composable(route = Screens.Search.name) {
            HotelSearch()
        }
        composable(route = Screens.Wishlist.name) {
            Wishlist()
        }
        composable(route = Screens.Profile.name) {
            Profile()
        }
    }
}
