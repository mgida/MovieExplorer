package com.example.movieexplorer.data.remote

import com.example.movieexplorer.data.dto.credits.MovieCreditsResponse
import com.example.movieexplorer.data.dto.movie_details.MovieDetailsResponse
import com.example.movieexplorer.data.dto.popular_movies.PopularMoviesResponse
import com.example.movieexplorer.data.dto.similar_movies.SimilarMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRemoteDataSource {
    @Authorized
    @GET("movie/popular")
    suspend fun getPopularMovies(): PopularMoviesResponse


    @Authorized
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
    ): PopularMoviesResponse

    @Authorized
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): MovieDetailsResponse

    @Authorized
    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int
    ): SimilarMoviesResponse

    @Authorized
    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int
    ): MovieCreditsResponse
}
