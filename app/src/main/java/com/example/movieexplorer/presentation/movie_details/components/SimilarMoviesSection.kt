package com.example.movieexplorer.presentation.movie_details.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieexplorer.domain.model.similar_movies.SimilarMovieModel
import com.example.movieexplorer.util.ThemePreviews

@Composable
fun SimilarMoviesSection(
    modifier: Modifier = Modifier,
    similarMovies: List<SimilarMovieModel>
) {
    LazyRow(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        items(similarMovies) { movie ->
            SimilarMovieCard(movie = movie)
        }
    }
}

@ThemePreviews
@Composable
fun SimilarMoviesSectionPreview() {
    val sampleMovies = listOf(
        SimilarMovieModel(
            id = 1,
            title = "Sample Movie 1",
            overview = "An exciting overview of Sample Movie 1.",
            image = ""
        ),
        SimilarMovieModel(
            id = 2,
            title = "Sample Movie 2",
            overview = "An exciting overview of Sample Movie 2.",
            image = ""
        ),
        SimilarMovieModel(
            id = 3,
            title = "Sample Movie 3",
            overview = "An exciting overview of Sample Movie 3.",
            image = ""
        )
    )

    SimilarMoviesSection(
        similarMovies = sampleMovies,
        modifier = Modifier.padding(8.dp)
    )
}
