package com.example.apiwithretrofit.api

import retrofit2.Response
import retrofit2.http.GET
//https://saurav.tech/NewsAPI/sources.json
interface PostApi {
    @GET("/sources.json")
    fun getAllTodos():Response<List<Post>>
}