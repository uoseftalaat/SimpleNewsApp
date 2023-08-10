package com.example.apiwithretrofit.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostRetrofit {
    val retrofitApi = Retrofit.Builder()
        .baseUrl("https://saurav.tech/NewsAPI/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PostApi::class.java)
}