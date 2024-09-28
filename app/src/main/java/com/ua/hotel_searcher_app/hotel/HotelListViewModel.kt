package com.ua.hotel_searcher_app.hotel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ua.hotel_searcher_app.apiManager.ApiServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelListViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiServiceImpl: ApiServiceImpl
) : ViewModel() {

    private var _loadHotels = MutableStateFlow(false)
    val loadHotels = _loadHotels.asStateFlow()

    private val _hotels = MutableStateFlow(listOf<HotelModel>())
    val hotels = _hotels.asStateFlow()

    private val _showRetry = MutableStateFlow(false)
    val showRetry = _showRetry.asStateFlow()

    init {
        loadHotels()
    }

    //API
    fun retryLoadingHotels() {
        Log.d("LoadHotel", "Retry loading hotels")
        loadHotels()
    }

    private fun loadHotels() {
        _loadHotels.value = true
        apiServiceImpl.getHotels(
            context = context,
            onSuccess = {
                Log.d("LoadHotel", "Success: ${it.size} hotels received")
                viewModelScope.launch {
                    try {
                        _hotels.emit(it)
                        Log.d("LoadHotel", "Hotels emitted successfully")
                    } catch (e: Exception) {
                        Log.e("LoadHotel", "Error emitting hotels: ${e.message}")
                    }
                }
                _showRetry.value = false
            },
            onFail = {
                Log.e("LoadHotel", "Failed to load hotels")
                _showRetry.value = true
            },
            loadingFinished = {
                _loadHotels.value = false
                Log.d("LoadHotels", "Loading finished")
            }
        )
    }


}
