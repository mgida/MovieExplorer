package com.example.movieexplorer.presentation.movie_details.viewstate

import com.example.movieexplorer.domain.model.movie_details.MovieDetailsModel

data class MovieDetailsViewState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: MovieDetailsModel? = null
)
