package com.example.apiwithretrofit.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.apiwithretrofit.api.Post
import com.example.apiwithretrofit.api.PostRetrofit
import com.example.apiwithretrofit.api.Source
import com.example.apiwithretrofit.room.Dao
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class MainRepo(private val dao:Dao) {
    suspend fun getOnlineData(): List<Source>? {
            val response = try {
                PostRetrofit.retrofitApi.getAllTodos()
            }catch (e: HttpException){
                Log.i("arsotnaosydn","http")
                return null
            }catch (e: IOException){
                Log.i("arsotnaosydn","ioEx")
                return null
            }
        if(response != null && response.isSuccessful){
            return response.body()!!.sources
        }
        else{
            return null
        }
    }

    suspend fun insertToFav(news:Source) = dao.insertToFav(news)
    suspend fun deleteFromFav(news:Source) = dao.deleteFromFav(news)
    suspend fun getAllFav():List<Source> = dao.getAllFav()

}