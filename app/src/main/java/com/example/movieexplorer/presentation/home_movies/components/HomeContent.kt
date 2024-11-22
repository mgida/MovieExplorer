package com.example.movieexplorer.presentation.home_movies.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieexplorer.presentation.home_movies.viewmodel.PopularMoviesViewModel
import timber.log.Timber


@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    viewModel: PopularMoviesViewModel = hiltViewModel(),
    onNavigateToDetails: (movieId: String) -> Unit
) {

    val popularMovies = viewModel.popularMoviesState.collectAsState().value

    SideEffect {
        Timber.d("movies: $popularMovies")
    }
}
