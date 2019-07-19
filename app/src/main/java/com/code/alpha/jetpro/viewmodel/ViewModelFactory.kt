package com.code.alpha.jetpro.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.code.alpha.jetpro.model.source.JetProRepository
import com.code.alpha.jetpro.di.Injection
import androidx.lifecycle.ViewModel
import com.code.alpha.jetpro.fragment.home.MovieViewModel
import java.lang.IllegalArgumentException


class ViewModelFactory(val jetProRepository: JetProRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object{
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory{
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ViewModelFactory(Injection.provideRepository(application) as JetProRepository)
                    }
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieViewModel::class.java)){
            return MovieViewModel(jetProRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: "+modelClass.name)
    }
}