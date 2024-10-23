package com.ua.innvista.hotel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.ua.innvista.R

@Composable
fun HotelDetailModal(
    hotel: HotelModel,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.dimensions_corner_radius)),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.dimensions_padding))
        ) {
            Column(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.dimensions_padding_big))
            ) {
                AsyncImage(
                    model = hotel.imgUrl,
                    contentDescription = hotel.title,
                    placeholder = painterResource(id = R.drawable.innvista),
                    error = painterResource(id = R.drawable.innvista),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.img_height))
                        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.dimensions_corner_radius))),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimensions_spacer_big)))

                Text(
                    text = hotel.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimensions_padding))
                )

                Text(
                    text = hotel.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimensions_padding))
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimensions_padding))
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = stringResource(id = R.string.location),
                        modifier = Modifier.size(dimensionResource(id = R.dimen.dimensions_icon)),
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.dimensions_spacer)))
                    Text(
                        text = hotel.location,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimensions_spacer_medium)))

                Text(
                    text = hotel.price,
                    style = MaterialTheme.typography.titleMedium,
                    color = colorResource(id = R.color.appBlue),
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dimensions_padding))
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimensions_spacer_medium)))

                TextButton(
                    onClick = { onDismissRequest() },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = colorResource(id = R.color.appBlue)
                    ),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = stringResource(id = R.string.close))
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHotelDetail() {
    HotelDetailModal(
        hotel = HotelModel(
            1L,
            "Cozy Apartment",
            "$120",
            "New York",
            "A beautiful place to stay in the city center.",
            ""
        ),
        onDismissRequest = {}
    )
}
