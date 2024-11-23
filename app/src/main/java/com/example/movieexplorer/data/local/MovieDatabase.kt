package com.example.movieexplorer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieexplorer.domain.model.movie_details.MovieDetailsModel


@Database(
    entities = [MovieDetailsModel::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {

    abstract val movieLocalDataSource: MovieLocalDataSource

    companion object {
        const val DATABASE_NAME = "movies_db"
    }
}