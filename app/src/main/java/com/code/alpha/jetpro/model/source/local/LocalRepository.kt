package com.code.alpha.jetpro.model.source.local

import androidx.lifecycle.LiveData
import com.code.alpha.jetpro.model.source.local.entity.Movie
import com.code.alpha.jetpro.model.source.local.room.MovieDao

class LocalRepository(private val movieDao: MovieDao?) {

    companion object{
        private var INSTANCE: LocalRepository? = null

        private fun getInstance(movieDao: MovieDao): LocalRepository?{
            if (INSTANCE == null){
                INSTANCE = LocalRepository(movieDao)
            }
            return INSTANCE
        }
    }

    fun getAllMovies(): LiveData<List<Movie>>?{
        return movieDao?.getMovies()
    }

    fun insertMovies(movies: List<Movie>){
        movieDao?.insertMovies(movies)
    }

    fun deleteMovie(movie: Movie){
        movieDao?.deleteMovies(movie)
    }

    fun updateFavoriteStatus(movie: Movie){
        movieDao?.updateFavoriteStatus(movie)
    }
}