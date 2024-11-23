package com.example.movieexplorer.presentation.home_movies.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieexplorer.presentation.common.EmptyStateMessage
import com.example.movieexplorer.presentation.common.ErrorMessage
import com.example.movieexplorer.presentation.common.LoadingIndicator
import com.example.movieexplorer.presentation.home_movies.event.SearchMoviesEvent
import com.example.movieexplorer.presentation.home_movies.viewmodel.PopularMoviesViewModel


private const val MIN_LENGTH = 3

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    viewModel: PopularMoviesViewModel = hiltViewModel(),
    onNavigateToDetails: (movieId: Int) -> Unit
) {

    val state = viewModel.popularMoviesState.collectAsState().value
    var searchQuery by remember { mutableStateOf("") }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            SearchBar(
                query = searchQuery,
                onQueryChanged = { query ->
                    searchQuery = query
                    if (query.length > MIN_LENGTH) {
                        viewModel.onEvent(SearchMoviesEvent.SearchMovies(query = query))
                    }
                }
            )

            when {
                state.isLoading -> LoadingIndicator(modifier = modifier)

                state.error.isNotBlank() -> ErrorMessage(modifier = modifier, message = state.error)

                state.data.isEmpty() -> EmptyStateMessage(
                    modifier = modifier,
                    message = "No movies found. Try searching again."
                )

                else -> MoviesContent(modifier, state.data) { movieId ->
                    onNavigateToDetails.invoke(movieId)
                }
            }
        }
    }
}





