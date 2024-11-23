package com.example.movieexplorer.presentation.movie_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.movieexplorer.domain.model.similar_movies.SimilarMovieModel
import com.example.movieexplorer.ui.theme.MovieExplorerTheme
import com.example.movieexplorer.util.ThemePreviews

@Composable
fun SimilarMovieCard(movie: SimilarMovieModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .width(150.dp)
            .padding(end = 8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(movie.image),
            contentDescription = "Similar Movie Poster",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            text = movie.title,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6F)
            ),
            modifier = Modifier.padding(vertical = 4.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@ThemePreviews
@Composable
fun SimilarMovieCardPreview() {
    MovieExplorerTheme {
        SimilarMovieCard(
            movie = SimilarMovieModel(
                id = 9498, title = "Inception",
                overview = "Awesome..",
                image = ""
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}
