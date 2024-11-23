package com.example.movieexplorer.presentation.movie_details.viewstate

import com.example.movieexplorer.domain.model.movie_credits.CastModel
import com.example.movieexplorer.domain.model.movie_credits.CrewModel


data class CreditsViewState(
    val isLoading: Boolean = false,
    val error: String = "",
    val actors: List<CastModel> = emptyList(),
    val directors: List<CrewModel> = emptyList()
)
