package com.example.movieexplorer.presentation

import com.example.movieexplorer.domain.model.popular_movies.PopularMovieModel

data class PopularMoviesState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<PopularMovieModel> = emptyList()
)
