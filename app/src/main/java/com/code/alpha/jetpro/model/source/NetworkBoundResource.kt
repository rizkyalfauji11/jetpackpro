package com.code.alpha.jetpro.model.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.code.alpha.jetpro.model.source.remote.ApiResponse
import com.code.alpha.jetpro.model.source.remote.StatusResponse
import com.code.alpha.jetpro.utils.AppExecutors
import com.code.alpha.jetpro.vo.Resource


abstract class NetworkBoundResource<ResultType, RequestType>(appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()
    private val executors: AppExecutors = appExecutors

    protected fun onFetchFailed() {

    }

    init {
        result.setValue(Resource.loading(null))
        val dbSource = loadFromDB()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData -> result.setValue(Resource.success(newData)) }
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(
            dbSource
        ) { newData -> result.setValue(Resource.loading(newData)) }

        result.addSource(apiResponse) { response ->

            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            when (response.statusResponse) {
                StatusResponse.SUCCESS -> executors.diskIO().execute {

                    saveCallResult(response.body)

                    executors.mainThread().execute {
                        result.addSource(
                            loadFromDB()
                        ) { newData -> result.setValue(Resource.success(newData)) }
                    }

                }

                StatusResponse.EMPTY -> executors.mainThread().execute {
                    result.addSource(
                        loadFromDB()
                    ) { newData -> result.setValue(Resource.success(newData)) }
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.error(newData, response.message)
                    }
                }
            }
        }


    }

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)
}