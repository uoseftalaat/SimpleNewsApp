package com.example.apiwithretrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiwithretrofit.api.PostRetrofit
import com.example.apiwithretrofit.databinding.ActivityMainBinding
import com.example.apiwithretrofit.recyclerview.PostRecyclerView
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var postAdapter:PostRecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        lifecycleScope.launchWhenCreated {
            val response = try {
                PostRetrofit.retrofitApi.getAllTodos()
            }catch (e:HttpException){
                Log.i("retro","http")
                return@launchWhenCreated

            }catch (e:IOException){
                Log.i("retro","ioEx")
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null){
                postAdapter.posts = response.body()!!.sources
            }
            else{
                Log.i("retro","fail")
            }
        }


    }


    fun setupRecyclerView() = binding.rvPost.apply {
        postAdapter = PostRecyclerView()
        adapter = postAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)


    }
}