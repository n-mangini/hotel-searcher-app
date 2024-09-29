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
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ua.innVista.R
import com.ua.innVista.utils.showToast
import com.ua.innVista.wishlist.WishlistViewModel

@Composable
fun HotelSearch() {
    val hotelSearchViewModel = hiltViewModel<HotelSearchViewModel>()
    val wishlistViewModel = hiltViewModel<WishlistViewModel>()

    val hotels by hotelSearchViewModel.hotels.collectAsState()
    val loading by hotelSearchViewModel.loadHotels.collectAsState()
    val showRetry by hotelSearchViewModel.showRetry.collectAsState()

    var selectedHotel by remember { mutableStateOf<HotelModel?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    Column {
        SearchBar(query = searchQuery, onQueryChanged = { searchQuery = it })

        when {
            loading -> LoadingView()
            showRetry -> RetryView(onRetry = { hotelSearchViewModel.retryLoadingHotels() })

            selectedHotel != null -> HotelDetail(hotel = selectedHotel!!)

            else -> HotelList(
                hotels = hotels.filter { hotel ->
                    hotel.title.contains(searchQuery, ignoreCase = true) || hotel.location.contains(
                        searchQuery,
                        ignoreCase = true
                    )
                },
                onHotelSelected = { selectedHotel = it },
                wishlistViewModel = wishlistViewModel
            )
        }
    }
}

@Composable
fun HotelList(
    hotels: List<HotelModel>,
    onHotelSelected: (HotelModel) -> Unit,
    wishlistViewModel: WishlistViewModel
) {
    val context = LocalContext.current

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(hotels) { hotel ->
            HotelItem(
                hotel = hotel,
                onItemClick = { onHotelSelected(hotel) },
                onIconClick = {
                    wishlistViewModel.addHotel(
                        title = hotel.title,
                        imgUrl = hotel.imgUrl,
                        location = hotel.location,
                        description = hotel.description,
                        price = hotel.price
                    ) { wasAdded ->
                        if (wasAdded) {
                            showToast(context, context.getString(R.string.added_to_wishlist))
                        } else {
                            showToast(context, context.getString(R.string.already_in_wishlist))
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun HotelItem(
    hotel: HotelModel,
    onItemClick: (HotelModel) -> Unit,
    onIconClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
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
                placeholder = painterResource(id = R.drawable.innvista),
                error = painterResource(id = R.drawable.innvista),
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
                    IconButton(
                        onClick = { onIconClick() }) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = stringResource(R.string.add_to_wishlist),
                            tint = colorResource(id = R.color.star),
                            modifier = Modifier
                                .size(30.dp)
                                .align(Alignment.Top)
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
fun SearchBar(query: String, onQueryChanged: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChanged,
            placeholder = { Text(text = stringResource(R.string.search_hotels)) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.Center),
            color = colorResource(id = R.color.appBlueLight),
            trackColor = colorResource(id = R.color.appBlue),
        )
    }
}

@Composable
fun RetryView(onRetry: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.retry),
            fontWeight = FontWeight.Bold,
        )
        Text(text = stringResource(R.string.loadhotels))
        Button(onClick = { onRetry() }) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Composable
@Preview
fun PreviewHotelItem() {
    HotelItem(
        hotel = HotelModel(
            title = "Hotel 1",
            imgUrl = "",
            location = "Location 1",
            description = "Description 1",
            price = "Price 1"
        ),
        onItemClick = {},
        onIconClick = {}
    )
}