package com.example.movieexplorer.domain.model.movie_details

data class MovieDetailsModel(
    val id: Int,
    val title: String,
    val overview: String,
    val image: String,
    val releaseDate: String,
    val status: String,
    val tagLine: String,
    val revenue: String,
)
