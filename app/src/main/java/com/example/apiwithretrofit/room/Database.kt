package com.example.apiwithretrofit.room

import androidx.room.Database
import com.example.apiwithretrofit.api.Source

@Database(entities = [Source::class], version = 1)
abstract class Database {
    abstract fun newsDao():Dao
}