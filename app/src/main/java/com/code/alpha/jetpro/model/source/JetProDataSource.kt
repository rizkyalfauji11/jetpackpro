package com.code.alpha.jetpro.model.source

import androidx.lifecycle.LiveData
import com.code.alpha.jetpro.model.source.local.entity.Movie
import com.code.alpha.jetpro.vo.Resource

interface JetProDataSource {
    fun getAllMovies(fileName: String): LiveData<Resource<List<Movie>>>

    fun setFavoriteMovie(movie: Movie)
}