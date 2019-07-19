package com.code.alpha.jetpro.di

import com.code.alpha.jetpro.model.source.remote.helper.JsonHelper
import com.code.alpha.jetpro.model.source.remote.RemoteRepository
import com.code.alpha.jetpro.model.source.local.LocalRepository
import android.app.Application
import com.code.alpha.jetpro.model.source.JetProRepository
import com.code.alpha.jetpro.model.source.local.room.JetProDatabase
import com.code.alpha.jetpro.utils.AppExecutors


object Injection {
    fun provideRepository(application: Application): JetProRepository? {
        val jetProDatabase = JetProDatabase.getInstance(application)
        val localRepository = LocalRepository(jetProDatabase?.movieDao())
        val remoteRepository = RemoteRepository.getInstance(JsonHelper(application))
        val appExecutors = AppExecutors()
        return JetProRepository.getInstance(localRepository, remoteRepository as RemoteRepository, appExecutors)
    }
}