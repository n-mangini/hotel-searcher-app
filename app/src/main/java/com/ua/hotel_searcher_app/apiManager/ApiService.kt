package com.ua.hotel_searcher_app.apiManager

import com.ua.hotel_searcher_app.publication.PublicationModel
import com.ua.hotel_searcher_app.publication.PublicationResponse
import retrofit.Call
import retrofit.http.GET

interface ApiService {
    @GET("hotels")
    fun getPublications(): Call<PublicationResponse>
}