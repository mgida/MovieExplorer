package com.example.movieexplorer.presentation.home_movies.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieexplorer.R
import com.example.movieexplorer.ui.theme.MovieExplorerTheme
import com.example.movieexplorer.util.ThemePreviews


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChanged,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = {
            Text(
                text = stringResource(R.string.search_movies), style =
                MaterialTheme.typography.labelMedium
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = ""
            )
        },
        singleLine = true
    )
}

@ThemePreviews
@Composable
fun SearchBarPreview() {
    MovieExplorerTheme {
        SearchBar(query = "") {}
    }
}
