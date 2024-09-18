package com.ua.hotel_searcher_app.publication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.ua.hotel_searcher_app.R

@Composable
fun Publication() {
    val viewModel: PublicationViewModel = viewModel()
    var selectedPublication by remember { mutableStateOf<PublicationModel?>(null) }

    if (selectedPublication != null) {
        PublicationDetail(publicationModel = selectedPublication!!)
    } else {
        LazyColumn {
            items(viewModel.publications.value) { publication ->
                PublicationView(
                    publication = publication,
                    onItemClick = { selectedPublication = publication }
                )
            }
        }
    }
}

//TODO reusable modifiers
@Composable
fun PublicationView(
    publication: PublicationModel,
    onItemClick: (PublicationModel) -> Unit
    //modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .clickable { onItemClick(publication) }
    ) {
        Image(
            painter = painterResource(id = publication.imageResId),
            contentDescription = publication.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = publication.title,
                )
                Text(
                    text = publication.location
                )
            }
            Button(onClick = { TODO() }) {
                Icon(imageVector = Icons.Filled.Star, contentDescription = "Save")
            }
        }
    }
}

@Preview
@Composable
fun PreviewPublicationView() {
    PublicationView(
        publication = PublicationModel(
            "Cozy Apartment",
            "A beautiful place to stay in the city center.",
            "New York",
            "$120/night",
            imageResId = R.drawable.image_1
        )
    ) {}
}
