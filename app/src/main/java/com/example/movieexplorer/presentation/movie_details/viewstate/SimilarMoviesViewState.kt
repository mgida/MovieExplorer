package com.example.movieexplorer.presentation.movie_details.viewstate

import com.example.movieexplorer.domain.model.similar_movies.SimilarMovieModel

data class SimilarMoviesViewState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<SimilarMovieModel> = listOf()
)
