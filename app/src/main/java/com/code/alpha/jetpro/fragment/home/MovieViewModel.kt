package com.code.alpha.jetpro.fragment.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.code.alpha.jetpro.model.Constant
import com.code.alpha.jetpro.model.Data.getJsonToString
import com.code.alpha.jetpro.model.source.JetProRepository
import com.code.alpha.jetpro.model.source.local.entity.Movie
import com.code.alpha.jetpro.model.source.remote.response.MoviesModel
import com.code.alpha.jetpro.vo.Resource
import com.google.gson.Gson

class MovieViewModel(private val jetProRepository: JetProRepository) : ViewModel() {

    private val moviesResult: MutableLiveData<Movie> = MutableLiveData()
    val movies: LiveData<Resource<List<Movie>>> = Transformations.switchMap(moviesResult) {
        Log.e("Terjadi", "YA")
        jetProRepository.getAllMovies(Constant.movie)
    }

}