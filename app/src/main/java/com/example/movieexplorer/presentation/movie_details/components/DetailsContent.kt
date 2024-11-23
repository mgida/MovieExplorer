package com.example.movieexplorer.presentation.movie_details.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieexplorer.presentation.common.EmptyStateMessage
import com.example.movieexplorer.presentation.common.ErrorMessage
import com.example.movieexplorer.presentation.common.LoadingIndicator
import com.example.movieexplorer.presentation.movie_details.viewmodel.MovieDetailsViewModel


@Composable
fun DetailsContent(
    modifier: Modifier = Modifier,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {

    val state = viewModel.movieDetailsState.collectAsState().value

    when {
        state.isLoading -> LoadingIndicator(modifier = modifier)

        state.error.isNotBlank() -> ErrorMessage(modifier = modifier, message = "")

        state.data == null -> EmptyStateMessage(modifier = modifier)

        else -> {
            MovieDetailsScreen(movieDetails = state.data)
        }
    }

}






