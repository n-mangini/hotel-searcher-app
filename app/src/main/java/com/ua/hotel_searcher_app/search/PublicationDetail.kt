package com.ua.hotel_searcher_app.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ua.hotel_searcher_app.publication.Publication

@Composable
fun PublicationDetail(publication: Publication) {
    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = painterResource(id = publication.imageResId),
            contentDescription = publication.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Text(
            text = publication.title,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = publication.description,
        )

        Text(
            text = "Location: ${publication.location}",
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}