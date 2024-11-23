package com.example.movieexplorer.data.dto.similar_movies

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SimilarMoviesResponse(
    val page: Int? = null,
    val results: List<SimilarMovieResponse>? = null,
    @Json(name = "total_pages")
    val totalPages: Int? = null,
    @Json(name = "total_results")
    val totalResults: Int? = null
)