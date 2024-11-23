package com.example.movieexplorer.di

import android.app.Application
import androidx.room.Room
import com.example.movieexplorer.data.local.MovieDatabase
import com.example.movieexplorer.data.local.MovieLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRecipeDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            MovieDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideRecipeLocalDataSource(recipeDatabase: MovieDatabase): MovieLocalDataSource =
        recipeDatabase.movieLocalDataSource

}