package com.velmurugan.mvvmwithkotlincoroutinesandretrofit.helpers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.repos.MainRepository
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.viewmodels.MainViewModel

class MyViewModelFactory constructor(private val repository: MainRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}