package com.example.movieexplorer.presentation.home_movies.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieexplorer.domain.model.popular_movies.PopularMoviesGroupedByYearModel
import com.example.movieexplorer.presentation.home_movies.viewmodel.PopularMoviesViewModel
import timber.log.Timber


@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    viewModel: PopularMoviesViewModel = hiltViewModel(),
    onNavigateToDetails: (movieId: String) -> Unit
) {

    val state = viewModel.popularMoviesState.collectAsState().value

    when {
        state.isLoading -> {}

        state.error.isNotBlank() -> {}

        state.data.isEmpty() -> {}

        else -> {
            MoviesResult(state.data)
        }
    }
}

@Composable
fun MoviesResult(moviesGroupedByYearModels: List<PopularMoviesGroupedByYearModel>) {

    SideEffect {
        moviesGroupedByYearModels.forEach { group ->
            Timber.d("Year: ${group.year} -> Movies: ${group.movies.joinToString { it.title }}")
        }
    }
}
