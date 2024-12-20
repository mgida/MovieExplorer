package com.example.movieexplorer.presentation.movie_details.event

sealed class MovieDetailsEvent {
    data class GetMoviesDetails(val movieId: Int) : MovieDetailsEvent()
    data class GetSimilarMovies(val movieId: Int) : MovieDetailsEvent()
    data class CheckWatchList(val movieId: Int) : MovieDetailsEvent()
}