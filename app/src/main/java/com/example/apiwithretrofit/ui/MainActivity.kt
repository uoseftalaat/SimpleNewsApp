package com.example.apiwithretrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiwithretrofit.databinding.ActivityMainBinding
import com.example.apiwithretrofit.recyclerview.PostRecyclerView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apiwithretrofit.api.Source


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var postAdapter:PostRecyclerView
    private val viewModel:MainViewModel by viewModels {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(application) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.news.observe(this) { it ->
            Log.i("the size of ",it.size.toString())
            setupRecyclerView()
            postAdapter.posts = it
        }
    }


    fun setupRecyclerView() = binding.rvPost.apply {
        postAdapter = PostRecyclerView()
        adapter = postAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)

    }
}