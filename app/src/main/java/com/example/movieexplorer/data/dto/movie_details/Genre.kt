package com.example.movieexplorer.data.dto.movie_details

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Genre(
    val id: Int? = null,
    val name: String? = null
)