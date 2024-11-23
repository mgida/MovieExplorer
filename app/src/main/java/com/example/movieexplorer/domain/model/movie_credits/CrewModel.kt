package com.example.movieexplorer.domain.model.movie_credits

data class CrewModel(
    val id: Int,
    val name: String,
    val job: String,
    val popularity: Double,
    val profilePath: String,
    val knownForDepartment: String
)