package com.example.movieexplorer.data.repo

import com.example.movieexplorer.data.local.MovieLocalDataSource
import com.example.movieexplorer.data.remote.MovieRemoteDataSource
import com.example.movieexplorer.domain.model.movie_details.MovieDetailsModel
import com.example.movieexplorer.domain.repo.MovieRepo

class MovieRepoImpl(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
) : MovieRepo {
    override suspend fun getPopularMovies() = remoteDataSource.getPopularMovies()
    override suspend fun searchMovies(query: String) = remoteDataSource.searchMovies(query)
    override suspend fun getMovieDetails(movieId: Int) = remoteDataSource.getMovieDetails(movieId)
    override suspend fun getSimilarMovies(movieId: Int) = remoteDataSource.getSimilarMovies(movieId)
    override suspend fun getMovieCredits(movieId: Int) = remoteDataSource.getMovieCredits(movieId)

    override fun getWatchList() = localDataSource.getWatchList()

    override suspend fun getMovieById(id: Int) = localDataSource.getMovieById(id)

    override suspend fun insertMovie(movie: MovieDetailsModel) {
        localDataSource.insertMovie(movie)
    }

    override suspend fun deleteMovie(movie: MovieDetailsModel) {
        localDataSource.deleteMovie(movie)
    }
}