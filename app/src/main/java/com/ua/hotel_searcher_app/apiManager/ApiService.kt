package com.ua.hotel_searcher_app.apiManager

import com.ua.hotel_searcher_app.publication.PublicationModel
import retrofit.Call
import retrofit.http.GET

interface ApiService {
    @GET("publications")
    fun getPublications(): Call<List<PublicationModel>>
}