package com.example.movieexplorer.data.remote

import com.example.movieexplorer.data.dto.popular_movies.PopularMoviesResponse
import retrofit2.http.GET

interface MovieRemoteDataSource {
    @Authorized
    @GET("movie/popular")
    suspend fun getPopularMovies(): PopularMoviesResponse

}