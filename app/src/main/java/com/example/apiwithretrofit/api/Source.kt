package com.example.apiwithretrofit.api

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Source(
    val category: String,
    val country: String,
    val description: String,
    @PrimaryKey
    val id: String,
    val language: String,
    val name: String,
    val url: String
)