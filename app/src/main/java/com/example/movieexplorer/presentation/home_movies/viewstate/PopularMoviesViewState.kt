package com.example.movieexplorer.presentation.home_movies.viewstate

import com.example.movieexplorer.domain.model.popular_movies.PopularMoviesGroupedByYearModel

data class PopularMoviesViewState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<PopularMoviesGroupedByYearModel> = listOf()
)
