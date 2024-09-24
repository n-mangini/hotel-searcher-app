package com.ua.hotel_searcher_app.publication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ua.hotel_searcher_app.R

@Composable
fun PublicationDetail(publication: PublicationModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = painterResource(R.drawable.image_1),
            contentDescription = publication.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Text(
            text = publication.title,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = publication.description,
        )
        Text(
            text = publication.location,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        Text(
            text = publication.price,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
fun PreviewPublicationDetail() {
    PublicationDetail(
        publication = PublicationModel(
            "Cozy Apartment",
            "$120/night",
            "New York",
            "A beautiful place to stay in the city center."
        )
    )
}