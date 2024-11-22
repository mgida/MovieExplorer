package com.example.movieexplorer.domain.repo

import com.example.movieexplorer.data.dto.popular_movies.PopularMoviesResponse

interface MovieRepo {
    suspend fun getPopularMovies(): PopularMoviesResponse
}