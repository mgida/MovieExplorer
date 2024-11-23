package com.example.movieexplorer.di

import com.example.movieexplorer.data.local.MovieLocalDataSource
import com.example.movieexplorer.data.remote.MovieRemoteDataSource
import com.example.movieexplorer.data.repo.MovieRepoImpl
import com.example.movieexplorer.domain.repo.MovieRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieRepoModule {
    @Provides
    @Singleton
    fun provideMovieRepo(
        remoteDataSource: MovieRemoteDataSource,
        localDataSource: MovieLocalDataSource,
    ): MovieRepo =
        MovieRepoImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )
}