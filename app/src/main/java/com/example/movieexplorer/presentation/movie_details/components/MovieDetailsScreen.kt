package com.example.movieexplorer.presentation.movie_details.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieexplorer.domain.model.movie_details.MovieDetailsModel


@Composable
fun MainDetailsMovie(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetailsModel,
    isInWatchlist: Boolean,
    onWatchlistToggle: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {

        item {
            MainDetailsSection(
                modifier = modifier,
                movieDetails = movieDetails,
                isInWatchList = isInWatchlist,
                onWatchListToggle = {
                    onWatchlistToggle.invoke()
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
