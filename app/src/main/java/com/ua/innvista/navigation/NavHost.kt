package com.ua.innvista.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ua.innvista.R
import com.ua.innvista.notifications.Notifications
import com.ua.innvista.profile.Profile
import com.ua.innvista.hotel.HotelSearch
import com.ua.innvista.wishlist.Wishlist

@Composable
fun NavHostComposable(innerPadding: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Buscar.name,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(dimensionResource(id = R.dimen.dimensions_padding))
    ) {
        composable(route = Screens.Notifiaciones.name) {
            Notifications()
        }
        composable(route = Screens.Buscar.name) {
            HotelSearch()
        }
        composable(route = Screens.Deseados.name) {
            Wishlist()
        }
        composable(route = Screens.Perfil.name) {
            Profile()
        }
    }
}
