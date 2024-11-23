package com.example.movieexplorer.presentation.movie_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieexplorer.presentation.common.EmptyStateMessage
import com.example.movieexplorer.presentation.common.ErrorMessage
import com.example.movieexplorer.presentation.common.LoadingIndicator
import com.example.movieexplorer.presentation.movie_details.viewmodel.MovieDetailsViewModel


private const val MAX_DISPLAYED_MOVIES = 5

@Composable
fun DetailsContent(
    modifier: Modifier = Modifier,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {

    val movieDetailsState = viewModel.movieDetailsState.collectAsState().value
    val similarMoviesState = viewModel.similarMoviesViewState.collectAsState().value

    when {
        movieDetailsState.isLoading -> LoadingIndicator(modifier = modifier)

        movieDetailsState.error.isNotBlank() -> ErrorMessage(
            modifier = modifier,
            message = movieDetailsState.error
        )

        movieDetailsState.data == null -> EmptyStateMessage(modifier = modifier)


        else -> {

            val movieDetails = movieDetailsState.data

            when {
                similarMoviesState.isLoading -> {
                    Column(modifier = modifier.fillMaxSize()) {
                        MovieDetailsScreen(movieDetails = movieDetails)
                        LoadingIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }

                similarMoviesState.error.isNotBlank() -> {
                    Column(modifier = modifier.fillMaxSize()) {
                        MovieDetailsScreen(movieDetails = movieDetails)
                        ErrorMessage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            message = similarMoviesState.error
                        )
                    }
                }

                similarMoviesState.data.isEmpty() -> {
                    Column(modifier = modifier.fillMaxSize()) {
                        MovieDetailsScreen(movieDetails = movieDetails)
                        EmptyStateMessage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }

                else -> {
                    val similarMovies = similarMoviesState.data.take(MAX_DISPLAYED_MOVIES)
                    MovieDetailsScreen(
                        movieDetails = movieDetails,
                        similarMovies = similarMovies
                    )
                }
            }
        }
    }
}








