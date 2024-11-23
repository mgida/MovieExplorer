package com.example.movieexplorer.data.dto.credits

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cast(
    val adult: Boolean? = null,
    @Json(name = "cast_id")
    val castId: Int,
    val character: String? = null,
    @Json(name = "credit_id")
    val creditId: String? = null,
    val gender: Int? = null,
    val id: Int,
    @Json(name = "known_for_department")
    val knownForDepartment: String? = null,
    val name: String? = null,
    val order: Int? = null,
    @Json(name = "original_name")
    val originalName: String? = null,
    val popularity: Double? = null,
    @Json(name = "profile_path")
    val profilePath: String? = null
)