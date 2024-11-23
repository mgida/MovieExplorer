package com.example.movieexplorer.domain.model.movie_details

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieDetailsModel(
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String,
    val image: String,
    val releaseDate: String,
    val status: String,
    val tagLine: String,
    val revenue: String,
)
