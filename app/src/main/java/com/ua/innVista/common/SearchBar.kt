package com.ua.innVista.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.ua.innVista.R

@Composable
fun SearchBar(query: String, onQueryChanged: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.dimensions_padding))
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChanged,
            placeholder = { Text(text = stringResource(R.string.search_hotels)) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}