package com.example.apiwithretrofit.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiwithretrofit.R
import com.example.apiwithretrofit.api.Source
import com.example.apiwithretrofit.databinding.FragmentApiBinding
import com.example.apiwithretrofit.databinding.FragmentFavBinding
import com.example.apiwithretrofit.recyclerview.FavRecyclerView
import java.util.zip.Inflater


class FavFragment : Fragment() {

    lateinit var binding: FragmentFavBinding
    lateinit var postAdapter:FavRecyclerView
    private val viewModel:MainViewModel by viewModels {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(context!!) as T
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavBinding.inflate(inflater, container, false)
        viewModel.getAllFav()
        viewModel.news.observe(viewLifecycleOwner){
            setupRecyclerView(it)
        }
        return binding.root
    }

    fun setupRecyclerView(posts:List<Source>) = binding.rvfav.apply {
        postAdapter = FavRecyclerView(posts,viewModel)
        adapter = postAdapter
        layoutManager = LinearLayoutManager(context)
    }


}