package com.code.alpha.jetpro.model.source.remote

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.code.alpha.jetpro.model.source.local.entity.Movie
import com.code.alpha.jetpro.model.source.remote.helper.JsonHelper
import com.code.alpha.jetpro.model.source.remote.response.MoviesModel


class RemoteRepository constructor(private val jsonHelper: JsonHelper) {
    private val SERVICE_LATENCY_IN_MILLIS: Long = 2000;

    companion object {
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(helper: JsonHelper): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository(helper)
            }
            return INSTANCE
        }
    }

    fun getMovies(fileName: String): LiveData<ApiResponse<List<Movie>>> {
        /*val handler = Handler()
        handler.postDelayed({ callback.onContentReceived(jsonHelper.loadMovies(fileName)) }, SERVICE_LATENCY_IN_MILLIS)*/
        val resultMovies: MutableLiveData<ApiResponse<List<Movie>>> = MutableLiveData()
        val handler = Handler()
        handler.postDelayed({
            resultMovies.value = ApiResponse.success(jsonHelper.loadMovies(fileName).movies)
        }, SERVICE_LATENCY_IN_MILLIS)

        return resultMovies
    }


    interface GetMoviesCallback {
        fun onContentReceived(moviesModel: MoviesModel)
        fun onDataNotAvailable()
    }
}