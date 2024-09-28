package com.ua.hotel_searcher_app.wishlist

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ua.hotel_searcher_app.data.HotelEntity

@Composable
fun Wishlist() {
    val viewModel = hiltViewModel<WishlistViewModel>()

    val wishlist by viewModel.wishlist.collectAsState(initial = emptyList())

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(wishlist) { hotel ->
            WishlistItem(hotel = hotel, onDeleteClick = {
                viewModel.deleteHotel(it)
            })
        }
    }
}

@Composable
fun WishlistItem(
    hotel: HotelEntity,
    onDeleteClick: (HotelEntity) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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
                    .size(100.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(2f)
            ) {
                Text(
                    text = hotel.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = hotel.location,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = hotel.price,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            IconButton(onClick = { onDeleteClick(hotel) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete from wishlist"
                )
            }
        }
    }
}
