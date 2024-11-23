package com.example.movieexplorer.data.dto.movie_details

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BelongsToCollection(
    @Json(name = "backdrop_path")
    val backdropPath: String? = null,
    val id: Int,
    val name: String? = null,
    @Json(name = "poster_path")
    val posterPath: String? = null
)