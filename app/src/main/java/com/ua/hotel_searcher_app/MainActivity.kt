package com.ua.hotel_searcher_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ua.hotel_searcher_app.navigation.BottomBar
import com.ua.hotel_searcher_app.navigation.NavHostComposable
import com.ua.hotel_searcher_app.navigation.NavigationDrawerSheet
import com.ua.hotel_searcher_app.navigation.TopBar
import com.ua.hotel_searcher_app.ui.theme.HotelSearcherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()

            HotelSearcherAppTheme {
                ModalNavigationDrawer(drawerContent = {
                    NavigationDrawerSheet {
                        scope.launch { drawerState.close() }
                    }
                }, drawerState = drawerState) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Scaffold(
                            topBar = {
                                TopBar(
                                    navController = navController,
                                    openDrawer = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }
                                )
                            },
                            bottomBar = {
                                BottomBar { navController.navigate(it) }
                            },
                        ) { innerPadding ->
                            NavHostComposable(innerPadding, navController)
                        }
                    }
                }
            }
        }
    }
}
