package com.ua.hotel_searcher_app.apiManager

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.ua.hotel_searcher_app.R
import com.ua.hotel_searcher_app.publication.PublicationModel
import com.ua.hotel_searcher_app.publication.PublicationResponse
import retrofit.Call
import retrofit.Callback
import retrofit.GsonConverterFactory
import retrofit.Response
import retrofit.Retrofit
import javax.inject.Inject

class ApiServiceImpl @Inject constructor() {
    fun getPublications(
        context: Context,
        onSuccess: (List<PublicationModel>) -> Unit,
        onFail: () -> Unit,
        loadingFinished: () -> Unit
    ) {
        Log.d("ApiServiceImpl", "getPublications started")

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(
                context.getString(R.string.publications_url)
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

        val service: ApiService = retrofit.create(ApiService::class.java)
        val call: Call<PublicationResponse> = service.getPublications()

        Log.d("ApiServiceImpl", "API call created")

        call.enqueue(object : Callback<PublicationResponse> {
            override fun onResponse(
                response: Response<PublicationResponse>?,
                retrofit: Retrofit?
            ) {
                loadingFinished()
                Log.d("ApiServiceImpl", "onResponse called")
                if (response?.isSuccess == true) {
                    Log.d("ApiServiceImpl", "Response successful: ${response.body()}")
                    val publicationResponse = response.body()
                    val publications: List<PublicationModel> = publicationResponse?.publications ?: emptyList()
                    onSuccess(publications)
                } else {
                    Log.d("ApiServiceImpl", "Response not successful")
                    onFailure(Exception("Bad request"))
                }
            }

            override fun onFailure(t: Throwable?) {
                Log.e("ApiServiceImpl", "API call failed", t)
                Toast.makeText(context, "Can't get publications", Toast.LENGTH_SHORT).show()
                onFail()
                Log.d("ApiServiceImpl", "Loading finished in onFailure")
                loadingFinished()
            }
        })
    }
}
