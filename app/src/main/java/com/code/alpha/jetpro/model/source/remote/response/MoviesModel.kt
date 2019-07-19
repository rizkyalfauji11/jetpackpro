package com.code.alpha.jetpro.model.source.remote.response

import com.code.alpha.jetpro.model.source.local.entity.Movie
import com.google.gson.annotations.SerializedName


data class MoviesModel(
    @SerializedName("movies")
    var movies: List<Movie>
)

