package com.example.apiwithretrofit.api

import androidx.room.Entity

@Entity
data class Source(
    val category: String,
    val country: String,
    val description: String,
    val id: String,
    val language: String,
    val name: String,
    val url: String
)