package com.ua.innVista.apiManager

import com.ua.innVista.hotel.HotelModel
import retrofit.Call
import retrofit.http.GET

interface ApiService {
    @GET("hotels")
    fun getHotels(): Call<List<HotelModel>>
}
