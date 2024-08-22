package com.ua.hotel_searcher_app.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import com.ua.hotel_searcher_app.publication.Publication
import com.ua.hotel_searcher_app.publication.PublicationItem
import com.ua.hotel_searcher_app.publication.publications

@Composable
fun Search() {
    
    PublicationList(publications = publications)
}

@Composable
fun PublicationList(publications: List<PublicationItem>) {
    LazyColumn {
        items(publications.size) { index ->
            Publication(publicationItem = publications[index])
        }
    }
}

@Preview
@Composable
fun PreviewSearch() {
    Search()
}
