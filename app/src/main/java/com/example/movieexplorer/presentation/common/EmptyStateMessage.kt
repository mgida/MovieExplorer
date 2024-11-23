package com.example.movieexplorer.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.movieexplorer.util.ThemePreviews

@Composable
fun EmptyStateMessage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No movies available.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@ThemePreviews
@Composable
fun EmptyStateMessagePreview() {
    EmptyStateMessage()
}