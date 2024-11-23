package com.example.movieexplorer.domain.model.movie_credits

data class CastModel(
    val id: Int,
    val name: String,
    val character: String,
    val popularity: Double,
    val profilePath: String,
    val knownForDepartment: String
)