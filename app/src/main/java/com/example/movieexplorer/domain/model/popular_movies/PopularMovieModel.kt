package com.example.movieexplorer.domain.model.popular_movies


data class PopularMovieModel(
    val id: Int,
    val title: String,
    val overview: String,
    val image: String,
    val releaseDate: String
)
