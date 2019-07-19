package com.code.alpha.jetpro.model.source

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import com.code.alpha.jetpro.model.Constant
import com.code.alpha.jetpro.model.source.local.LocalRepository
import com.code.alpha.jetpro.model.source.local.entity.Movie
import com.code.alpha.jetpro.model.source.remote.ApiResponse
import com.code.alpha.jetpro.model.source.remote.RemoteRepository
import com.code.alpha.jetpro.utils.AppExecutors
import com.code.alpha.jetpro.vo.Resource


class JetProRepository(
    @NonNull val localRepository: LocalRepository,
    @NonNull val remoteRepository: RemoteRepository,
    val appExecutors: AppExecutors
) : JetProDataSource {

    companion object {
        @Volatile
        private var INSTANCE: JetProRepository? = null

        fun getInstance(
            localRepository: LocalRepository,
            remoteRepository: RemoteRepository,
            appExecutors: AppExecutors
        ): JetProRepository {
            if (INSTANCE == null) {
                synchronized(JetProRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = JetProRepository(localRepository, remoteRepository, appExecutors)
                    }
                }
            }
            return INSTANCE as JetProRepository
        }
    }

    override fun getAllMovies(fileName: String): LiveData<Resource<List<Movie>>> {

        return object : NetworkBoundResource<List<Movie>, List<Movie>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Movie>> {
                return localRepository.getAllMovies() as LiveData<List<Movie>>
            }

            override fun shouldFetch(data: List<Movie>): Boolean {
                return data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> {
                return remoteRepository.getMovies(Constant.movie)
            }

            override fun saveCallResult(data: List<Movie>) {
                val moviesEntity = ArrayList<Movie>()
                moviesEntity.addAll(data)
                localRepository.insertMovies(moviesEntity)
            }

        }.asLiveData()
    }

    override fun setFavoriteMovie(movie: Movie) {
        val runnable = Runnable {
            movie.status = !movie.status
            localRepository.updateFavoriteStatus(movie)
        }

        appExecutors.diskIO().execute(runnable)
    }

}
