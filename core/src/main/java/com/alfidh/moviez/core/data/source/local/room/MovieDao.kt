package com.alfidh.moviez.core.data.source.local.room

import androidx.room.*
import com.alfidh.moviez.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao {

    @Query("SELECT * FROM top_movie")
    fun getAllMovie() : Flow<List<MovieEntity>>

    @Query("SELECT * FROM top_movie WHERE favorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}