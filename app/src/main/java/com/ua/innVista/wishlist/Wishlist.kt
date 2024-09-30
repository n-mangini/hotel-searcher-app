package com.ua.innVista.wishlist

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ua.innVista.R
import com.ua.innVista.common.HotelItem
import com.ua.innVista.data.toModel
import com.ua.innVista.hotel.HotelDetailModal
import com.ua.innVista.hotel.HotelModel
import com.ua.innVista.utils.showToast

@Composable
fun Wishlist() {
    val viewModel = hiltViewModel<WishlistViewModel>()

    val wishlist by viewModel.wishlist.collectAsState(initial = emptyList())
    var selectedHotel by remember { mutableStateOf<HotelModel?>(null) }

    when {
        wishlist.isEmpty() -> WishlistEmpty()
        selectedHotel != null -> HotelDetailModal(
            hotel = selectedHotel!!,
            onDismissRequest = { selectedHotel = null })

        else -> WishlistContent(
            wishlist = wishlist.map { it.toModel() },
            onHotelSelected = { selectedHotel = it },
            viewModel = viewModel
        )
    }
}

@Composable
fun WishlistContent(
    wishlist: List<HotelModel>,
    onHotelSelected: (HotelModel) -> Unit,
    viewModel: WishlistViewModel
) {
    val context = LocalContext.current

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(wishlist) { hotel ->
            HotelItem(
                hotel = hotel,
                onItemClick = { onHotelSelected(hotel) },
                iconButtonComposable = {
                    RemoveFromWishlistIcon(
                        onIconClick = { handleRemoveFromWishlist(context, viewModel, hotel) }
                    )
                }
            )
        }
    }
}

@Composable
fun RemoveFromWishlistIcon(onIconClick: () -> Unit) {
    IconButton(onClick = { onIconClick() }) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = stringResource(R.string.delete_from_wishlist)
        )
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

fun handleRemoveFromWishlist(context: Context, viewModel: WishlistViewModel, hotel: HotelModel) {
    viewModel.deleteHotel(hotel.id)
    showToast(context, context.getString(R.string.removed_from_wishlist))
}

@Composable
@Preview
fun PreviewHotelItem() {
    HotelItem(
        hotel = HotelModel(
            id = 1L,
            title = "Hotel 1",
            imgUrl = "",
            location = "Location 1",
            description = "Description 1",
            price = "$912"
        ),
        onItemClick = {},
        iconButtonComposable = { RemoveFromWishlistIcon { } }
    )
}
