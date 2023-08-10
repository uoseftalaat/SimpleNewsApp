package com.example.apiwithretrofit.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.apiwithretrofit.api.Post
import com.example.apiwithretrofit.api.Source
import com.example.apiwithretrofit.databinding.PostviewBinding

class PostRecyclerView:RecyclerView.Adapter<PostRecyclerView.PostViewHolder>(){
    inner class PostViewHolder(val binding: PostviewBinding):RecyclerView.ViewHolder(binding.root)
    private val diffCallBack = object : DiffUtil.ItemCallback<Source>() {
        override fun areItemsTheSame(oldItem: Source, newItem: Source): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Source, newItem: Source): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this,diffCallBack)
    var posts:List<Source>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(PostviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.binding.apply {
            name.text = posts[position].name
            category.text = posts[position].category
            description.text = posts[position].description
        }
    }
}