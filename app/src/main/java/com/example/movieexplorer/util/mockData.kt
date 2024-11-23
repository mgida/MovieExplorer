package com.example.movieexplorer.util

import com.example.movieexplorer.domain.model.popular_movies.PopularMovieModel
import com.example.movieexplorer.domain.model.popular_movies.PopularMoviesGroupedByYearModel


fun getMockMoviesGroupedByYear(): List<PopularMoviesGroupedByYearModel> {
    return listOf(
        PopularMoviesGroupedByYearModel(
            year = "2024",
            movies = getMockPopularMovies()
        ),
        PopularMoviesGroupedByYearModel(
            year = "2023",
            movies = getMockPopularMovies()
        )
    )
}

fun getMockPopularMovies() = listOf(
    getMockPopularMovie(),
    getMockPopularMovie()
)

fun getMockPopularMovie() = PopularMovieModel(
    title = "The Shawshank Redemption",
    overview = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
    image = "https://image.tmdb.org/t/p/w500/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
    releaseDate = "1994-09-23"
)
