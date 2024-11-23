package com.example.movieexplorer.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieexplorer.domain.model.movie_details.MovieDetailsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieDetailsModel)

    @Delete
    suspend fun deleteMovie(movie: MovieDetailsModel)

    @Query("SELECT * FROM MovieDetailsModel")
    fun getWatchList(): Flow<List<MovieDetailsModel>>

    @Query("SELECT * FROM MovieDetailsModel WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieDetailsModel?
}