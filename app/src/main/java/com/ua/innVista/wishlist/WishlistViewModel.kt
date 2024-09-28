package com.ua.innVista.wishlist

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.ua.innVista.data.AppDatabase
import com.ua.innVista.data.HotelEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    @ApplicationContext val context: Context,
) : ViewModel() {
    private val appDatabase = AppDatabase.getDatabase(context)

    val wishlist = appDatabase.hotelDao().getAllHotels().asFlow()

    fun addHotel(
        title: String,
        imgUrl: String,
        location: String,
        description: String,
        price: String
    ) {
        viewModelScope.launch {
            // Check if the hotel already exists
            val existingHotel = appDatabase.hotelDao().getHotelByTitle(title)

            if (existingHotel == null) {
                // Only add hotel if it doesn't already exist
                val hotel = HotelEntity(
                    title = title,
                    imgUrl = imgUrl,
                    location = location,
                    description = description,
                    price = price
                )
                appDatabase.hotelDao().insert(hotel)
                Log.d("AddWishlist", "Hotel added to wishlist")
            } else {
                Log.d("AddWishlist", "Hotel already exists in the wishlist")
            }
        }
    }


    fun deleteHotel(hotel: HotelEntity) {
        viewModelScope.launch {
            appDatabase.hotelDao().delete(hotel)
        }
    }
}
