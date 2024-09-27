package com.ua.hotel_searcher_app.apiManager

import com.ua.hotel_searcher_app.hotel.HotelModel
import retrofit.Call
import retrofit.http.GET

interface ApiService {
    @GET("hotels")
    fun getHotels(): Call<List<HotelModel>>
}