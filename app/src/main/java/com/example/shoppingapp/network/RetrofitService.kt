package com.example.shoppingapp.network

import com.example.shoppingapp.model.Store
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("products")
    fun getAllProducts(): Call<List<Store>>

    companion object {
        var retrofitService: RetrofitService? = null
        val BASE_URL = "https://fakestoreapi.com/"

        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofitService = retrofit.create(RetrofitService::class.java)
            }

            return retrofitService!!
        }
    }

}