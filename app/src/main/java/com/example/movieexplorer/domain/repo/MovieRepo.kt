package com.example.movieexplorer.domain.repo

import com.example.movieexplorer.data.dto.credits.MovieCreditsResponse
import com.example.movieexplorer.data.dto.movie_details.MovieDetailsResponse
import com.example.movieexplorer.data.dto.popular_movies.PopularMoviesResponse
import com.example.movieexplorer.data.dto.similar_movies.SimilarMoviesResponse
import com.example.movieexplorer.domain.model.movie_details.MovieDetailsModel
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    suspend fun getPopularMovies(): PopularMoviesResponse
    suspend fun searchMovies(query: String): PopularMoviesResponse
    suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse
    suspend fun getSimilarMovies(movieId: Int): SimilarMoviesResponse
    suspend fun getMovieCredits(movieId: Int): MovieCreditsResponse

    fun getWatchList(): Flow<List<MovieDetailsModel>>
    suspend fun getMovieById(id: Int): MovieDetailsModel?
    suspend fun insertMovie(movie: MovieDetailsModel)
    suspend fun deleteMovie(movie: MovieDetailsModel)
}