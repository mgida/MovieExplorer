package com.example.movieexplorer.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.movieexplorer.R
import com.example.movieexplorer.ui.theme.MovieExplorerTheme
import com.example.movieexplorer.util.ThemePreviews

@Composable
fun EmptyStateMessage(
    modifier: Modifier = Modifier,
    message: String = stringResource(R.string.no_data_available)
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
    }
}

@ThemePreviews
@Composable
fun EmptyStateMessagePreview() {
    MovieExplorerTheme {
        EmptyStateMessage(message = "No movies available.")
    }
}