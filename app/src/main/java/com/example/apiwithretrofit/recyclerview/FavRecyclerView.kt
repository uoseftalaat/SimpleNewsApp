package com.example.apiwithretrofit.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.apiwithretrofit.api.Source
import com.example.apiwithretrofit.databinding.PostviewBinding
import com.example.apiwithretrofit.ui.MainViewModel

class FavRecyclerView(val posts:List<Source>, val viewModel: MainViewModel):RecyclerView.Adapter<FavRecyclerView.FavViewHolder>(){
    inner class FavViewHolder(val binding: PostviewBinding):RecyclerView.ViewHolder(binding.root)

    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        context = parent.context
        return FavViewHolder(PostviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.binding.apply {
            name.text = posts[position].name
            category.text = posts[position].category
            description.text = posts[position].description
            favButton.setOnClickListener{
                viewModel.deleteNews(posts[position])
                Toast.makeText(context,"Deleted from favorite",Toast.LENGTH_SHORT).show()
                viewModel.getAllFav()
            }
        }
    }
}