package com.example.apiwithretrofit.api

import retrofit2.Response
import retrofit2.http.GET

interface PostApi {
    @GET("sources.json")
    suspend fun getAllTodos():Response<Post>
}