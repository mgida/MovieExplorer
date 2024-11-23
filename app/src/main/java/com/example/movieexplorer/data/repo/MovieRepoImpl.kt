package com.example.movieexplorer.data.repo

import com.example.movieexplorer.data.remote.MovieRemoteDataSource
import com.example.movieexplorer.domain.repo.MovieRepo

class MovieRepoImpl(
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepo {
    override suspend fun getPopularMovies() = remoteDataSource.getPopularMovies()
    override suspend fun searchMovies(query: String) = remoteDataSource.searchMovies(query)
    override suspend fun getMovieDetails(movieId: Int) = remoteDataSource.getMovieDetails(movieId)
    override suspend fun getSimilarMovies(movieId: Int) = remoteDataSource.getSimilarMovies(movieId)
}