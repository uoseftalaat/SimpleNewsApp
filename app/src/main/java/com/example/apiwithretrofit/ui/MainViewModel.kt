package com.example.apiwithretrofit.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.apiwithretrofit.api.Source
import com.example.apiwithretrofit.other.Constant.DATABASE_NAME
import com.example.apiwithretrofit.repo.MainRepo
import com.example.apiwithretrofit.room.NewsDatabase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) :ViewModel(){

    private val repo:MainRepo
    var _news = MutableLiveData<List<Source>>()
    val news: LiveData<List<Source>>
        get() = _news

    init {
        val db = NewsDatabase.getInstance(application.applicationContext)
        repo = MainRepo(db.newsDao())
        getOnlineData()
    }

    fun getOnlineData(){
        viewModelScope.launch {
            _news = MutableLiveData(repo.getOnlineData())
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