package com.example.apiwithretrofit.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.apiwithretrofit.api.Source

@Database(entities = [Source::class], version = 1)
abstract class NewsDatabase:RoomDatabase() {
    abstract fun newsDao():Dao

    companion object {
        private var instance: NewsDatabase? = null
        @Synchronized
        fun getInstance(ctx: Context): NewsDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, NewsDatabase::class.java,
                    "note_database")
                    .fallbackToDestructiveMigration()
                    .build()

            return instance!!

        }
    }
}