package com.ua.hotel_searcher_app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ua.hotel_searcher_app.screens.Profile
import com.ua.hotel_searcher_app.screens.Search

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
        composable(route = Screens.Search.name) {
            Search()
        }
        composable(route = Screens.Whishlist.name) {
            // Whishlist()
        }
        composable(route = Screens.Profile.name) {
            Profile()
        }
    }
}