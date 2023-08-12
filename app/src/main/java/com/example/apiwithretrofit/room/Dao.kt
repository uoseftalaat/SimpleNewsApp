package com.example.apiwithretrofit.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.apiwithretrofit.api.Source

@Dao
interface Dao {
    @Insert
    fun insertToFav(news: Source)

    @Delete
    fun deleteFromFav(news: Source)

    @Query("SELECT * FROM Source")
    fun getAllFav(): List<Source>
}