package com.ua.innvista

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
import com.ua.innvista.navigation.BottomBar
import com.ua.innvista.navigation.NavHostComposable
import com.ua.innvista.navigation.NavigationDrawerSheet
import com.ua.innvista.navigation.TopBar
import com.ua.innvista.ui.theme.InnVistaTheme
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

            InnVistaTheme {
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
