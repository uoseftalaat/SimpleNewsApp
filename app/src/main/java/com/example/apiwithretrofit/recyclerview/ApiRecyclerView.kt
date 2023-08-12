package com.example.apiwithretrofit.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.apiwithretrofit.api.Source
import com.example.apiwithretrofit.databinding.PostviewBinding
import com.example.apiwithretrofit.ui.MainViewModel

class ApiRecyclerView(private val posts:List<Source>, private val viewModel: MainViewModel)
    :RecyclerView.Adapter<ApiRecyclerView.ApiViewHolder>(){
    inner class ApiViewHolder(val binding: PostviewBinding):RecyclerView.ViewHolder(binding.root)

    lateinit var context:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiViewHolder {
        context = parent.context
        return ApiViewHolder(PostviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: ApiViewHolder, position: Int) {
        holder.binding.apply {
            name.text = posts[position].name
            category.text = posts[position].category
            description.text = posts[position].description
            favButton.setOnClickListener{
                viewModel.insertNews(posts[position])
                viewModel.getOnlineData()
                Toast.makeText(context,"inserted to favorite",Toast.LENGTH_SHORT).show()
            }
        }
    }
}