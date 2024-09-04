package com.ua.hotel_searcher_app.search

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ua.hotel_searcher_app.publication.Publication
import com.ua.hotel_searcher_app.publication.PublicationViewModel

@Composable
fun Search() {
    val viewModel: PublicationViewModel = viewModel()
    PublicationList(viewModel = viewModel)
}

@Composable
fun PublicationList(viewModel: PublicationViewModel = viewModel()) {
    val publications = viewModel.publications.collectAsState().value

    LazyColumn {
        items(publications.size) { index ->
            Publication(publication = publications[index])
            Button(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Star, contentDescription = "Save")
            }
        }
    }
}

@Preview
@Composable
fun PreviewSearch() {
    Search()
}
