package com.velmurugan.mvvmwithkotlincoroutinesandretrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.viewmodels.MainViewModel
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.MovieAdapter
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.helpers.MyViewModelFactory
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.network.RetrofitService
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.databinding.ActivityMainBinding
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.repos.MainRepository

class MainActivity : AppCompatActivity() {
   private lateinit var viewModel: MainViewModel
    private val adapter = MovieAdapter()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)
        binding.recyclerview.adapter = adapter

        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(MainViewModel::class.java)


        viewModel.movieList.observe(this) {
            adapter.setMovies(it)
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        viewModel.getAllMovies()

    }
}