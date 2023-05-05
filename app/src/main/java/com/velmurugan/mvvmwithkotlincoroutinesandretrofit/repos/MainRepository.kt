package com.velmurugan.mvvmwithkotlincoroutinesandretrofit.repos

import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.Movie
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.network.NetworkState
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.network.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllMovies() : NetworkState<List<Movie>> {
            val response = retrofitService.getAllMovies()
            return if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    NetworkState.Success(responseBody)
                } else {
                    NetworkState.Error(response)
                }
            } else {
                NetworkState.Error(response)
            }
        }

}