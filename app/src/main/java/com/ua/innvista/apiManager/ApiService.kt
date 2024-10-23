package com.ua.innvista.apiManager

import com.ua.innvista.hotel.HotelModel
import retrofit.Call
import retrofit.http.GET

interface ApiService {
    @GET("hotels")
    fun getHotels(): Call<List<HotelModel>>
}
