package com.example.movieexplorer.presentation.movie_details.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieexplorer.domain.model.similar_movies.SimilarMovieModel

@Composable
fun SimilarMoviesSection(
    modifier: Modifier = Modifier,
    similarMovies: List<SimilarMovieModel>
) {
    Text(
        text = "Similar Movies",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )

    LazyRow(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        items(similarMovies) { movie ->
            SimilarMovieCard(movie = movie)
        }
    }
}