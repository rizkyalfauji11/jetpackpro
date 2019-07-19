package com.code.alpha.jetpro.fragment.home

import com.code.alpha.jetpro.model.source.local.entity.Movie

interface MovieCallback {
    fun showMovies(list: List<Movie>?)
}