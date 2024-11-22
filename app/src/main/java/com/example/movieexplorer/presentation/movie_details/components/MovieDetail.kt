package com.example.movieexplorer.presentation.movie_details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MovieDetail(modifier: Modifier) {

    Box(modifier = modifier.padding(16.dp), contentAlignment = Alignment.Center) {
        Text(text = "Details", style = MaterialTheme.typography.titleMedium)
    }
}