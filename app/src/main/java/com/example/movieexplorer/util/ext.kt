package com.example.movieexplorer.util


import android.os.Build
import com.example.movieexplorer.domain.model.popular_movies.PopularMovieModel
import java.time.LocalDate
import java.time.format.DateTimeParseException

private const val DEFAULT_YEAR = "2024"

fun List<PopularMovieModel>.moviesGroupedByYear(defaultYear: String = DEFAULT_YEAR): Map<String, List<PopularMovieModel>> {
    return this.groupBy { movie ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                LocalDate.parse(movie.releaseDate).year.toString()
            } catch (e: DateTimeParseException) {
                defaultYear
            }
        } else {
            try {
                movie.releaseDate.substring(0, 4)
            } catch (e: Exception) {
                defaultYear
            }
        }
    }
}