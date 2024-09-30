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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    @ApplicationContext val context: Context,
) : ViewModel() {
    private val appDatabase = AppDatabase.getDatabase(context)

    val wishlist = appDatabase.hotelDao().getAllHotels().asFlow()

    fun addHotel(
        id: Long,
        title: String,
        imgUrl: String,
        location: String,
        description: String,
        price: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val isAdded = withContext(Dispatchers.IO) {
                val existingHotel = appDatabase.hotelDao().getHotelByTitle(title)

                if (existingHotel == null) {
                    val hotelEntity = HotelEntity(
                        id = id,
                        title = title,
                        imgUrl = imgUrl,
                        location = location,
                        description = description,
                        price = price
                    )
                    appDatabase.hotelDao().insert(hotelEntity)
                    true
                } else {
                    false
                }
            }
            onResult(isAdded)
        }
    }


    fun deleteHotel(hotel: HotelEntity) {
        viewModelScope.launch {
            appDatabase.hotelDao().delete(hotel)
        }
    }
}
