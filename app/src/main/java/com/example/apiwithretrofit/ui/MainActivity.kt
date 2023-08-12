package com.example.apiwithretrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiwithretrofit.databinding.ActivityMainBinding
import com.example.apiwithretrofit.recyclerview.FavRecyclerView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apiwithretrofit.R
import com.example.apiwithretrofit.api.Source


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val viewModel:MainViewModel by viewModels {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(this@MainActivity) as T
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val apiFragment = ApiFragment()
        val favFragment = FavFragment()

        setCurrentFragment(apiFragment)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.favButton -> {
                    viewModel.getAllFav()
                    setCurrentFragment(favFragment)
                }
                R.id.apibutton -> {
                    viewModel.getOnlineData()
                    setCurrentFragment(apiFragment)
                }
            }
            true
        }
    }

    fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.fragmentlayout,fragment)
        commit()
    }



}