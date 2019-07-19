package com.code.alpha.jetpro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.code.alpha.jetpro.model.source.JetProDataSource
import com.code.alpha.jetpro.model.source.JetProRepository
import com.code.alpha.jetpro.model.source.local.LocalRepository
import com.code.alpha.jetpro.model.source.remote.RemoteRepository
import com.code.alpha.jetpro.model.source.remote.response.MoviesModel

class FakeJetProRepository(val localRepository: LocalRepository,
                           val remoteRepository: RemoteRepository
) : JetProDataSource {

    companion object {
        @Volatile
        private var INSTANCE: JetProRepository? = null

        fun getInstance(localRepository: LocalRepository, remoteRepository: RemoteRepository): JetProRepository {
            if (INSTANCE == null) {
                synchronized(JetProRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = JetProRepository(localRepository, remoteRepository)
                    }
                }
            }
            return INSTANCE!!
        }
    }

    override fun getAllMovies(fileName: String): LiveData<MoviesModel> {
        val moviesResult: MutableLiveData<MoviesModel> = MutableLiveData()
        remoteRepository.getMovies(object : RemoteRepository.GetMoviesCallback {
            override fun onContentReceived(moviesModel: MoviesModel) {
                moviesResult.postValue(moviesModel)
            }

            override fun onDataNotAvailable() {
            }

        }, fileName)
        return moviesResult
    }

}
