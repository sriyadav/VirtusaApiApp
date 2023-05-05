package com.velmurugan.mvvmwithkotlincoroutinesandretrofit.utils

import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.Movie

object ValidationUtil {

    fun validateMovie(movie: Movie) : Boolean {
        if (movie.name.isNotEmpty() && movie.category.isNotEmpty()) {
            return true
        }
        return false
    }
}