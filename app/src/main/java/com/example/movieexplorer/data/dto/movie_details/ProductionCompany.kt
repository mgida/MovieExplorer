package com.example.movieexplorer.data.dto.movie_details

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCompany(
    val id: Int? = null,
    @Json(name = "logo_path")
    val logoPath: String? = null,
    val name: String? = null,
    @Json(name = "origin_country")
    val originCountry: String? = null
)