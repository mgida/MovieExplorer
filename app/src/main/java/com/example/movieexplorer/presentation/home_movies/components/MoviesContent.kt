package com.example.movieexplorer.presentation.home_movies.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieexplorer.domain.model.popular_movies.PopularMoviesGroupedByYearModel
import com.example.movieexplorer.util.ThemePreviews
import com.example.movieexplorer.util.getMockMoviesGroupedByYear
import timber.log.Timber

@Composable
fun MoviesContent(
    modifier: Modifier = Modifier,
    moviesGroupedByYear: List<PopularMoviesGroupedByYearModel>
) {
    SideEffect {
        moviesGroupedByYear.forEach { group ->
            Timber.d("Year: ${group.year} -> Movies: ${group.movies.joinToString { it.title }}")
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(moviesGroupedByYear) { group ->
            YearSection(year = group.year, movies = group.movies)
        }
    }
}

@ThemePreviews
@Composable
fun MoviesContentPreview() {
    val mockMoviesGroupedByYear = getMockMoviesGroupedByYear()

    MaterialTheme {
        MoviesContent(
            moviesGroupedByYear = mockMoviesGroupedByYear,
            modifier = Modifier.padding(16.dp)
        )
    }
}
