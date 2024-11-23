package com.example.movieexplorer.domain.model.popular_movies

data class PopularMoviesGroupedByYearModel(
    val year: String,
    val movies: List<PopularMovieModel>
)
