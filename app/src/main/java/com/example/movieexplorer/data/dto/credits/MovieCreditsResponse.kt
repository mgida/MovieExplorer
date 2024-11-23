package com.example.movieexplorer.data.dto.credits

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieCreditsResponse(
    val cast: List<Cast>? = null,
    val crew: List<Crew>? = null,
    val id: Int? = null
)