package com.ua.innVista.wishlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ua.innVista.R
import com.ua.innVista.data.HotelEntity
import com.ua.innVista.hotel.HotelModel
import com.ua.innVista.utils.showToast

@Composable
fun Wishlist() {
    val viewModel = hiltViewModel<WishlistViewModel>()

    val wishlist by viewModel.wishlist.collectAsState(initial = emptyList())

    if (wishlist.isEmpty()) {
        WishlistEmpty()
    } else {
        WishlistContent(wishlist = wishlist, viewModel = viewModel)
    }
}

@Composable
fun WishlistContent(wishlist: List<HotelEntity>, viewModel: WishlistViewModel) {
    val context = LocalContext.current

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(wishlist) { hotel ->
            HotelCard(
                hotel = hotel,
                onDeleteClick = {
                    viewModel.deleteHotel(it)
                    showToast(context, context.getString(R.string.removed_from_wishlist))
                })
        }
    }
}

//similar to HotelCard in HotelList, but with delete button
@Composable
fun HotelCard(
    hotel: HotelEntity,
    onDeleteClick: (HotelEntity) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = hotel.imgUrl,
                contentDescription = hotel.title,
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = hotel.title,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.Top)
                    )
                    IconButton(onClick = { onDeleteClick(hotel) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(R.string.delete_from_wishlist)
                        )
                    }
                }

                Row {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = stringResource(R.string.location),
                        modifier = Modifier
                            .size(16.dp)
                            .align(Alignment.Top)
                    )

                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = hotel.location,
                        modifier = Modifier.align(Alignment.Top)
                    )
                }

                Spacer(modifier = Modifier.size(4.dp))

                Text(text = hotel.description)

                Spacer(modifier = Modifier.size(4.dp))

                Text(
                    text = hotel.price,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .align(Alignment.End)
                )
            }
        }
    }
}

@Composable
fun WishlistEmpty() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.your_wishlist_is_empty),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview
fun PreviewWishlistEmpty() {
    WishlistEmpty()
}

