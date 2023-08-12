package com.example.apiwithretrofit.ui

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.apiwithretrofit.api.PostRetrofit
import com.example.apiwithretrofit.api.Source
import com.example.apiwithretrofit.other.Constant.DATABASE_NAME
import com.example.apiwithretrofit.repo.MainRepo
import com.example.apiwithretrofit.room.NewsDatabase
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainViewModel(val context:Context) :ViewModel(){

    private lateinit var repo:MainRepo
    private var _news = MutableLiveData<List<Source>>()
    val news: LiveData<List<Source>>
        get() = _news

    init {
        viewModelScope.launch {
            val db = NewsDatabase.getInstance(context)
            repo = MainRepo(db.newsDao())
        }
    }

    fun getOnlineData() {
        viewModelScope.launch {
            val response = PostRetrofit.retrofitApi.getAllTodos()

            if (response != null && response.isSuccessful) {
                _news.value = response.body()?.sources
            }
        }
    }

    fun insertNews(news:Source) = viewModelScope.launch {
        repo.insertToFav(news)
    }

    fun deleteNews(news: Source) = viewModelScope.launch {
        repo.deleteFromFav(news)
    }

    fun getAllFav() = viewModelScope.launch {
        _news = MutableLiveData(repo.getAllFav())
    }

}