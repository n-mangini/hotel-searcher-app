package com.ua.innVista.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NavigationDrawerSheet(
    onClose: () -> Unit,
) {
    ModalDrawerSheet {
        Column(modifier = Modifier.padding(vertical = 20.dp, horizontal = 16.dp)) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "",
                modifier = Modifier.clickable { onClose() }
            )
        }
    }
}