package com.example.movieexplorer.presentation.home_movies.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieexplorer.presentation.home_movies.event.SearchMoviesEvent
import com.example.movieexplorer.presentation.home_movies.viewmodel.PopularMoviesViewModel


private const val MIN_LENGTH = 3

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    viewModel: PopularMoviesViewModel = hiltViewModel(),
    onNavigateToDetails: (movieId: String) -> Unit
) {

    val state = viewModel.popularMoviesState.collectAsState().value
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize()) {
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

            state.error.isNotBlank() -> ErrorMessage(modifier = modifier, message = "")

            state.data.isEmpty() -> EmptyStateMessage(modifier = modifier)

            else -> MoviesContent(modifier, state.data)
        }
    }
}





