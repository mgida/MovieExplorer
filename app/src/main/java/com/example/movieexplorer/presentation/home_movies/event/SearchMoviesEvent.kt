package com.example.movieexplorer.presentation.home_movies.event

sealed class SearchMoviesEvent {
    data class SearchMovies(val query: String) : SearchMoviesEvent()
    data object GetPopularMovies : SearchMoviesEvent()

}