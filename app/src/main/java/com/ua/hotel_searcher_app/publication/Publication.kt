package com.ua.hotel_searcher_app.publication

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ua.hotel_searcher_app.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.ua.hotel_searcher_app.ui.theme.PurpleGrey40
import com.ua.hotel_searcher_app.ui.theme.PurpleGrey80

@Composable
fun Publication() {
    val viewModel = hiltViewModel<PublicationViewModel>()

    val publications by viewModel.publications.collectAsState()
    val loading by viewModel.loadPublications.collectAsState()
    val showRetry by viewModel.showRetry.collectAsState()

    var selectedPublication by remember { mutableStateOf<PublicationModel?>(null) }

    if (loading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.Center),
                color = PurpleGrey40,
                trackColor = PurpleGrey80,
            )
        }
    } else if (showRetry) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "retry",
                fontWeight = FontWeight.Bold,
            )
            Text(text = "loadRanking")
            Button(onClick = { viewModel.retryLoadingRanking() }) {
                Text(text = "retry")
            }
        }
    } else {


        if (selectedPublication != null) {
            PublicationDetail(publicationModel = selectedPublication!!)
        } else {
            LazyColumn {
                items(publications) { publication ->
                    PublicationView(
                        publication = publication,
                        onItemClick = { selectedPublication = publication }
                    )
                }
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

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 0.dp)
            .clickable { onItemClick(publication) },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){

        Column(
            modifier = Modifier
                .clickable { onItemClick(publication) }
        ) {
            Image(
                painter = painterResource(R.drawable.image_1),
                contentDescription = publication.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 0.dp)
                    .height(200.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = publication.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Row {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = "Location",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = publication.location
                        )
                    }
                    Text(
                        text = publication.description
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = publication.price,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Button(onClick = { TODO() }, modifier = Modifier.align(Alignment.Top)) {
                    Icon(imageVector = Icons.Filled.Star, contentDescription = "Save")
                }
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
            "$120/night",
            "New York",
            "A beautiful place to stay in the city center."
        )
    ) {}
}
