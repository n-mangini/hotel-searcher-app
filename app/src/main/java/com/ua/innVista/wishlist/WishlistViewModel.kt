package com.ua.innVista.wishlist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.ua.innVista.data.AppDatabase
import com.ua.innVista.data.HotelEntity
import com.ua.innVista.hotel.HotelModel
import com.ua.innVista.hotel.toEntity
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

    fun addHotel(hotelModel: HotelModel, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val isAdded = withContext(Dispatchers.IO) {
                val existingHotel = appDatabase.hotelDao().getById(hotelModel.id)

                if (existingHotel == null) {
                    val hotelEntity = hotelModel.toEntity()
                    appDatabase.hotelDao().insert(hotelEntity)
                    true
                } else {
                    false
                }
            }
            onResult(isAdded)
        }
    }


    fun deleteHotel(hotelId: Long) {
        viewModelScope.launch {
            appDatabase.hotelDao().deleteById(hotelId)
        }
    }
}
