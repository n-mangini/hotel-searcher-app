package com.ua.hotel_searcher_app.wishlist

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.ua.hotel_searcher_app.data.AppDatabase
import com.ua.hotel_searcher_app.data.HotelEntity
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
        val hotel = HotelEntity(
            title = title,
            imgUrl = imgUrl,
            location = location,
            description = description,
            price = price
        )
        viewModelScope.launch {
            appDatabase.hotelDao().insert(hotel)
            Log.d("AddWishlist","Hotel added to wishlist")
        }
    }

    fun deleteHotel(hotel: HotelEntity) {
        viewModelScope.launch {
            appDatabase.hotelDao().delete(hotel)
        }
    }
}
