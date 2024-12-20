package com.example.movieexplorer.presentation.home_movies.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieexplorer.R
import com.example.movieexplorer.domain.model.popular_movies.PopularMovieModel
import com.example.movieexplorer.ui.theme.MovieExplorerTheme
import com.example.movieexplorer.util.ThemePreviews
import com.example.movieexplorer.util.getMockPopularMovies

@Composable
fun YearSection(
    modifier: Modifier = Modifier,
    year: String,
    movies: List<PopularMovieModel>,
    isInWatchlist: (Int) -> Boolean,
    onMovieClicked: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = stringResource(R.string.year, year),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        movies.forEach { movie ->
            MovieCard(movie = movie, isInWatchlist = isInWatchlist(movie.id)) { movieId ->
                onMovieClicked.invoke(movieId)
            }
        }
    }
}


@ThemePreviews
@Composable
fun YearSectionPreview() {
    val mockMovies = getMockPopularMovies()

    MovieExplorerTheme {
        YearSection(
            year = "2024",
            movies = mockMovies,
            modifier = Modifier.padding(16.dp),
            isInWatchlist = { true }
        ) {}
    }
}
