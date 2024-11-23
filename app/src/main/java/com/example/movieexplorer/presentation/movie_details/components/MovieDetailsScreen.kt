package com.example.movieexplorer.presentation.movie_details.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieexplorer.domain.model.movie_details.MovieDetailsModel
import com.example.movieexplorer.domain.model.similar_movies.SimilarMovieModel
import com.example.movieexplorer.util.ThemePreviews
import timber.log.Timber


@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetailsModel,
    similarMovies: List<SimilarMovieModel> = emptyList()
) {

    SideEffect {
        Timber.d("Similar Movies $similarMovies")
    }

    LazyColumn(modifier = modifier.fillMaxSize()) {

        item {
            MainDetailsSection(modifier = modifier, movieDetails)
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (similarMovies.isNotEmpty()) {
            item {
                SimilarMoviesSection(modifier, similarMovies)
            }
        }
    }
}


@ThemePreviews
@Composable
fun MovieDetailsPreview() {
    MovieDetailsScreen(
        movieDetails = MovieDetailsModel(
            id = 1,
            title = "Avengers: Endgame",
            overview = "The Avengers assemble once more to undo the damage Thanos has caused to the universe.",
            image = "",
            releaseDate = "2019-04-26",
            status = "Released",
            tagLine = "Avenge the fallen.",
            revenue = "$2.798 billion"
        )
    )
}
