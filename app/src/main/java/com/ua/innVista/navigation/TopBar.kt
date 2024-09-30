package com.ua.innVista.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ua.innVista.R
import com.ua.innVista.common.TabBarBadgeView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController,
    openDrawer: () -> Unit,
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    val showBackButton by remember(currentBackStackEntry) {
        derivedStateOf {
            navController.previousBackStackEntry != null && !basePages.contains(navController.currentDestination?.route)
        }
    }

    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = if(!showBackButton) Icons.Filled.Menu else Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 20.dp)
                    .clickable {
                        if (showBackButton) {
                            navController.popBackStack()
                        } else {
                            openDrawer()
                        }
                    }
            )
        },
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        actions = {
            Row(horizontalArrangement = Arrangement.spacedBy(25.dp)) {
                BadgedBox(badge = { TabBarBadgeView(912) }) {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "",
                        modifier = Modifier.clickable { navController.navigate(Screens.Notifications.name) }
                    )
                }
            }
        },
        modifier = Modifier.padding(horizontal = 10.dp),
    )
}
