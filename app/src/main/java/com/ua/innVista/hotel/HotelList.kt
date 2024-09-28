package com.ua.innVista.hotel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ua.innVista.ui.theme.PurpleGrey40
import com.ua.innVista.ui.theme.PurpleGrey80
import com.ua.innVista.wishlist.WishlistViewModel

@Composable
fun HotelList() {
    val hotelListviewModel = hiltViewModel<HotelListViewModel>()
    val wishlistViewModel = hiltViewModel<WishlistViewModel>()

    val hotels by hotelListviewModel.hotels.collectAsState()
    val loading by hotelListviewModel.loadHotels.collectAsState()
    val showRetry by hotelListviewModel.showRetry.collectAsState()

    var selectedHotel by remember { mutableStateOf<HotelModel?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    Column {
        // Add the SearchBar
        SearchBar(query = searchQuery, onQueryChanged = { searchQuery = it })

        when {
            loading -> LoadingView()
            showRetry -> RetryView(hotelListviewModel)

            selectedHotel != null -> HotelDetail(hotel = selectedHotel!!)

            else -> HotelListContent(
                hotels = hotels.filter { hotel ->
                    hotel.title.contains(searchQuery, ignoreCase = true) || hotel.location.contains(searchQuery, ignoreCase = true)
                },
                onHotelSelected = { selectedHotel = it },
                wishlistViewModel = wishlistViewModel
            )
        }
    }
}

@Composable
fun SearchBar(query: String, onQueryChanged: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChanged,
            placeholder = { Text(text = "Search hotels...") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
fun HotelListContent(
    hotels: List<HotelModel>,
    onHotelSelected: (HotelModel) -> Unit,
    wishlistViewModel: WishlistViewModel
) {
    LazyColumn {
        items(hotels) { hotel ->
            HotelView(
                hotel = hotel,
                onItemClick = { onHotelSelected(hotel) },
                wishlistViewModel = wishlistViewModel
            )
        }
    }
}

@Composable
fun HotelView(
    hotel: HotelModel,
    onItemClick: (HotelModel) -> Unit,
    wishlistViewModel: WishlistViewModel
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(hotel) },
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
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Save",
                        tint = Color(0xFFFFD700),
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.Top)
                            .clickable {
                                wishlistViewModel.addHotel(
                                    title = hotel.title,
                                    imgUrl = hotel.imgUrl,
                                    location = hotel.location,
                                    description = hotel.description,
                                    price = hotel.price
                                )
                            }
                    )
                }

                Row {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Location",
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
fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.Center),
            color = PurpleGrey40,
            trackColor = PurpleGrey80,
        )
    }
}

@Composable
fun RetryView(viewModel: HotelListViewModel) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "retry",
            fontWeight = FontWeight.Bold,
        )
        Text(text = "loadHotels")
        Button(onClick = { viewModel.retryLoadingHotels() }) {
            Text(text = "retry")
        }
    }
}

/*@Preview
@Composable
fun PreviewHotelView() {
    HotelView(
        hotel = HotelModel(
            "Wyndham Garden Campana",
            "60700",
            "Campana, Buenos Aires",
            "Alojamiento informal con restaurante y spa",
            ""
        ), wishlistViewModel = WishlistViewModel()
    ) {}
}*/
