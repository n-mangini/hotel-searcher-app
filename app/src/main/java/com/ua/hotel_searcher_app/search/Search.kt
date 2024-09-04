package com.ua.hotel_searcher_app.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ua.hotel_searcher_app.publication.Publication
import com.ua.hotel_searcher_app.publication.PublicationViewModel

@Composable
fun Search() {
    val viewModel: PublicationViewModel = viewModel()
    var selectedPublication by remember { mutableStateOf<Publication?>(null) }

    if (selectedPublication != null) {
        PublicationDetail(publication = selectedPublication!!)
    } else {
        PublicationList(
            publications = viewModel.publications.collectAsState().value,
            onItemClick = { publication ->
                selectedPublication = publication
            }
        )
    }
}

@Composable
fun PublicationList(
    publications: List<Publication>,
    onItemClick: (Publication) -> Unit
) {
    LazyColumn {
        items(publications.size) { index ->
            Image(
                painter = painterResource(id = publications[index].imageResId),
                contentDescription = publications[index].title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Row(
                modifier = Modifier
                    .clickable { onItemClick(publications[index]) }
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = publications[index].title,
                    )
                    Text(
                        text = publications[index].location
                    )
                }
                Button(onClick = { TODO() }) {
                    Icon(imageVector = Icons.Filled.Star, contentDescription = "Save")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSearch() {
    Search()
}
