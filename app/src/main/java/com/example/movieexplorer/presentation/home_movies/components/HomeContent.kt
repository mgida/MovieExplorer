package com.example.movieexplorer.presentation.home_movies.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieexplorer.presentation.home_movies.viewmodel.PopularMoviesViewModel


@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    viewModel: PopularMoviesViewModel = hiltViewModel(),
    onNavigateToDetails: (movieId: String) -> Unit
) {

    val state = viewModel.popularMoviesState.collectAsState().value

    when {
        state.isLoading -> LoadingIndicator(modifier = modifier)

        state.error.isNotBlank() -> ErrorMessage(modifier = modifier, message = "")

        state.data.isEmpty() -> EmptyStateMessage(modifier = modifier)

        else -> {
            MoviesContent(modifier, state.data)
        }
    }
}








