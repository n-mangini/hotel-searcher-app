package com.ua.hotel_searcher_app.publication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
fun PublicationDetail(publicationModel: PublicationModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = painterResource(id = publicationModel.imageResId),
            contentDescription = publicationModel.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Text(
            text = publicationModel.title,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = publicationModel.description,
        )

        Text(
            text = "Location: ${publicationModel.location}",
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

@Preview
@Composable
fun PreviewPublicationDetail() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        PublicationDetail(publicationModel = PublicationModel("Hello World", "Title", "Description", "999", R.drawable.image_1))
    }
}