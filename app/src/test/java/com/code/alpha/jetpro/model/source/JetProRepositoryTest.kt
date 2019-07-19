package com.code.alpha.jetpro.model.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.code.alpha.jetpro.FakeDataDummy
import com.code.alpha.jetpro.FakeJetProRepository
import com.code.alpha.jetpro.model.Constant
import com.code.alpha.jetpro.model.source.local.LocalRepository
import com.code.alpha.jetpro.model.source.remote.RemoteRepository
import com.code.alpha.jetpro.model.source.remote.response.MoviesModel
import com.code.alpha.jetpro.utils.LiveDataTestUtil
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times


class JetProRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var localRepository = mock(LocalRepository::class.java)
    private var remoteRepository = mock(RemoteRepository::class.java)
    private var jetProRepository = FakeJetProRepository(localRepository, remoteRepository)

    private var moviesResponse: MoviesModel =
        Gson().fromJson(FakeDataDummy.getJsonToString(Constant.movie), MoviesModel::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteRepository.GetMoviesCallback)
                .onContentReceived(moviesResponse)
            null
        }.`when`<RemoteRepository>(remoteRepository)
            .getMovies(any(), any())


        val movies: MoviesModel = LiveDataTestUtil.getValue(jetProRepository.getAllMovies(Constant.movie))
        verify(remoteRepository, times(1)).getMovies(
            any(), any()
        )
        assertEquals(moviesResponse.movies?.size, movies.movies?.size)
    }
}