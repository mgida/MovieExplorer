package com.example.movieexplorer.presentation.home_movies.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieexplorer.domain.model.popular_movies.PopularMoviesGroupedByYearModel
import com.example.movieexplorer.ui.theme.MovieExplorerTheme
import com.example.movieexplorer.util.ThemePreviews
import com.example.movieexplorer.util.getMockMoviesGroupedByYear
import timber.log.Timber

@Composable
fun MoviesContent(
    modifier: Modifier = Modifier,
    moviesGroupedByYear: List<PopularMoviesGroupedByYearModel>,
    onMovieClicked: (Int) -> Unit
) {
    SideEffect {
        moviesGroupedByYear.forEach { group ->
            Timber.d("Year: ${group.year} -> Movies: ${group.movies.joinToString { it.title }}")
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(moviesGroupedByYear) { group ->
            YearSection(year = group.year, movies = group.movies) { movieId ->
                onMovieClicked.invoke(movieId)
            }
        }
    }
}

@ThemePreviews
@Composable
fun MoviesContentPreview() {
    val mockMoviesGroupedByYear = getMockMoviesGroupedByYear()

    MovieExplorerTheme {
        MoviesContent(
            moviesGroupedByYear = mockMoviesGroupedByYear,
            modifier = Modifier.padding(16.dp)
        ) {}
    }
}
