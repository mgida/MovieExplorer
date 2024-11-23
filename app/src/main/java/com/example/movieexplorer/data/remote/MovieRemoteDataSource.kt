package com.example.movieexplorer.data.remote

import com.example.movieexplorer.data.dto.popular_movies.PopularMoviesResponse
import retrofit2.http.GET
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
}