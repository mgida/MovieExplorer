package com.example.movieexplorer.presentation.movie_details.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieexplorer.R
import com.example.movieexplorer.domain.model.movie_credits.CastModel
import com.example.movieexplorer.domain.model.movie_credits.CrewModel
import com.example.movieexplorer.domain.model.similar_movies.SimilarMovieModel
import com.example.movieexplorer.presentation.common.EmptyStateMessage
import com.example.movieexplorer.presentation.common.ErrorMessage
import com.example.movieexplorer.presentation.common.LoadingIndicator
import com.example.movieexplorer.presentation.movie_details.viewmodel.MovieDetailsViewModel
import com.example.movieexplorer.presentation.movie_details.viewstate.CreditsViewState
import com.example.movieexplorer.presentation.movie_details.viewstate.SimilarMoviesViewState
import kotlinx.coroutines.flow.collectLatest


private const val MAX_DISPLAYED_MOVIES = 5

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {

    val movieDetailsState = viewModel.movieDetailsState.collectAsState().value
    val similarMoviesState = viewModel.similarMoviesViewState.collectAsState().value
    val creditsState = viewModel.creditsViewState.collectAsState().value

    val isInWatchlist = viewModel.isInWatchlist.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }

    val savedMovieMsg = stringResource(id = R.string.add_to_watch_list)
    val removedMovieMsg = stringResource(id = R.string.remove_from_watch_list)

    LaunchedEffect(key1 = viewModel.eventFlow) {
        viewModel.eventFlow.collectLatest { value: MovieDetailsViewModel.UiEvent ->
            when (value) {
                is MovieDetailsViewModel.UiEvent.RemoveMovieFromWatchList -> {
                    snackbarHostState.showSnackbar(
                        message = removedMovieMsg
                    )
                }

                is MovieDetailsViewModel.UiEvent.SaveMovieToWatchList -> {
                    snackbarHostState.showSnackbar(
                        message = savedMovieMsg
                    )
                }
            }
        }
    }



    when {
        movieDetailsState.isLoading -> LoadingIndicator(modifier = modifier)

        movieDetailsState.error.isNotBlank() -> ErrorMessage(
            modifier = modifier,
            message = movieDetailsState.error
        )

        movieDetailsState.data == null -> EmptyStateMessage(modifier = modifier)

        else -> {
            val movieDetails = movieDetailsState.data
            val similarMovies = similarMoviesState.data.take(MAX_DISPLAYED_MOVIES)
            val actors = creditsState.actors
            val directors = creditsState.directors



            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) },
                content = { paddingValues ->
                    Surface(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {

                            item {
                                MainDetailsMovie(
                                    movieDetails = movieDetails,
                                    isInWatchlist = isInWatchlist,
                                    onWatchlistToggle = {
                                        viewModel.toggleWatchlist(movieDetails)
                                    }
                                )
                            }

                            item { Spacer(modifier = Modifier.height(16.dp)) }

                            item { SimilarMoviesContent(similarMoviesState, similarMovies) }

                            item { Spacer(modifier = Modifier.height(16.dp)) }

                            item { TopCreditsContent(creditsState, actors, directors) }

                        }
                    }
                }
            )

        }
    }
}

@Composable
private fun TopCreditsContent(
    creditsState: CreditsViewState,
    actors: List<CastModel>,
    directors: List<CrewModel>
) {
    if (creditsState.isLoading) {
        LoadingIndicator(modifier = Modifier.fillMaxWidth())
    } else if (creditsState.error.isNotBlank()) {
        ErrorMessage(
            modifier = Modifier.fillMaxWidth(),
            message = creditsState.error
        )
    } else {
        TopCreditsSection(
            actors = actors,
            directors = directors
        )
    }
}

@Composable
private fun SimilarMoviesContent(
    similarMoviesState: SimilarMoviesViewState,
    similarMovies: List<SimilarMovieModel>
) {
    if (similarMoviesState.isLoading) {
        LoadingIndicator(modifier = Modifier.fillMaxWidth())
    } else if (similarMoviesState.error.isNotBlank()) {
        ErrorMessage(
            modifier = Modifier.fillMaxWidth(),
            message = similarMoviesState.error
        )
    } else if (similarMoviesState.data.isEmpty()) {
        EmptyStateMessage(modifier = Modifier.fillMaxWidth())
    } else {
        SimilarMoviesSection(similarMovies = similarMovies)
    }
}








