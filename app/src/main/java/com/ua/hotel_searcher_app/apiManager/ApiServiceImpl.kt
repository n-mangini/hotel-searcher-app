package com.ua.hotel_searcher_app.apiManager

import android.content.Context
import android.widget.Toast
import com.ua.hotel_searcher_app.R
import com.ua.hotel_searcher_app.publication.PublicationModel
import retrofit.Call
import retrofit.Callback
import retrofit.GsonConverterFactory
import retrofit.Response
import retrofit.Retrofit
import javax.inject.Inject

class ApiServiceImpl @Inject constructor() {
    fun getPublications(
        context: Context, onSuccess: (List<PublicationModel>) -> Unit,
        onFail: () -> Unit,
        loadingFinished: () -> Unit
    ) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(
                context.getString(R.string.publications_url)
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
        val service: ApiService = retrofit.create(ApiService::class.java)
        val call: Call<List<PublicationModel>> = service.getPublications()
        call.enqueue(object : Callback<List<PublicationModel>> {
            override fun onResponse(
                response: Response<List<PublicationModel>>?,
                retrofit: Retrofit?
            ) {
                loadingFinished()
                if (response?.isSuccess == true) {
                    val jokes: List<PublicationModel> = response.body()
                    onSuccess(jokes)
                } else {
                    onFailure(Exception("Bad request"))
                }
            }

            override fun onFailure(t: Throwable?) {
                Toast.makeText(context, "Can't get publications", Toast.LENGTH_SHORT).show()
                onFail()
                loadingFinished()
            }
        })
    }
}
