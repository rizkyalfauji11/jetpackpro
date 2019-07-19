package com.code.alpha.jetpro.fragment.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.code.alpha.jetpro.FakeDataDummy
import com.code.alpha.jetpro.model.Constant
import com.code.alpha.jetpro.model.source.JetProRepository
import com.code.alpha.jetpro.model.source.local.entity.Movie
import com.code.alpha.jetpro.model.source.remote.response.MoviesModel
import com.google.gson.Gson
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*


class MovieViewModelTest : MovieCallback {
    private lateinit var movieViewModel: MovieViewModel
    private var jetProRepository: JetProRepository = mock(JetProRepository::class.java)

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(jetProRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getData() {
        val liveMovies: MutableLiveData<MoviesModel> = MutableLiveData()
        liveMovies.value = Gson().fromJson(FakeDataDummy.getJsonToString(Constant.movie), MoviesModel::class.java)
        `when`(jetProRepository.getAllMovies(Constant.movie)).thenReturn(liveMovies)
        val observer = mock(Observer::class.java) as Observer<MoviesModel>
        movieViewModel.getMovies(Constant.movie).observeForever(observer)
        verify(jetProRepository).getAllMovies(Constant.movie)
    }

    override fun showMovies(list: List<Movie>?) {
        /*assertNotNull(list)
        assertEquals(10, list?.size)
        assertEquals(dataList[2].title, list?.get(2)?.title)*/
    }
}