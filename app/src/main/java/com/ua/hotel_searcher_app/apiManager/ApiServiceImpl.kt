package com.ua.hotel_searcher_app.apiManager

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.ua.hotel_searcher_app.R
import com.ua.hotel_searcher_app.hotel.HotelModel
import retrofit.Call
import retrofit.Callback
import retrofit.GsonConverterFactory
import retrofit.Response
import retrofit.Retrofit
import javax.inject.Inject

class ApiServiceImpl @Inject constructor() {
    fun getHotels(
        context: Context,
        onSuccess: (List<HotelModel>) -> Unit,
        onFail: () -> Unit,
        loadingFinished: () -> Unit
    ) {
        Log.d("ApiServiceImpl", "getHotels started")

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(
                context.getString(R.string.hotels_url)
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

        val service: ApiService = retrofit.create(ApiService::class.java)
        val call: Call<List<HotelModel>> = service.getHotels()

        Log.d("ApiServiceImpl", "API call created")

        call.enqueue(object : Callback<List<HotelModel>> {
            override fun onResponse(
                response: Response<List<HotelModel>>?,
                retrofit: Retrofit?
            ) {
                loadingFinished()
                Log.d("ApiServiceImpl", "onResponse called")
                if (response?.isSuccess == true) {
                    Log.d("ApiServiceImpl", "Response successful: ${response.body()}")
                    val hotels: List<HotelModel> = response.body()
                    onSuccess(hotels)
                } else {
                    Log.d("ApiServiceImpl", "Response not successful")
                    onFailure(Exception("Bad request"))
                }
            }

            override fun onFailure(t: Throwable?) {
                Log.e("ApiServiceImpl", "API call failed", t)
                Toast.makeText(context, "Can't get hotels", Toast.LENGTH_SHORT).show()
                onFail()
                Log.d("ApiServiceImpl", "Loading finished in onFailure")
                loadingFinished()
            }
        })
    }
}
