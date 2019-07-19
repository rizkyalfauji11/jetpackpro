package com.code.alpha.jetpro.model.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.code.alpha.jetpro.model.source.local.entity.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM tb_favorite")
    fun getMovies(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>): LongArray

    @Delete
    fun deleteMovies(movie: Movie)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateFavoriteStatus(movie: Movie)

}