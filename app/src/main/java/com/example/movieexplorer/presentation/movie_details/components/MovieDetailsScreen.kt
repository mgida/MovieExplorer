package com.example.movieexplorer.presentation.movie_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.movieexplorer.domain.model.movie_details.MovieDetailsModel
import com.example.movieexplorer.util.ThemePreviews


@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetailsModel
) {
    Column(modifier = modifier.fillMaxSize()) {
        Image(
            painter = rememberAsyncImagePainter(movieDetails.image),
            contentDescription = "Movie Poster",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            text = movieDetails.title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = movieDetails.tagLine,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Text(
            text = movieDetails.overview,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = "Release Date: ${movieDetails.releaseDate}",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = "Status: ${movieDetails.status}",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = "Revenue: ${movieDetails.revenue}",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}


@ThemePreviews
@Composable
fun MovieDetailsPreview() {
    MovieDetailsScreen(
        movieDetails = MovieDetailsModel(
            id = 1,
            title = "Avengers: Endgame",
            overview = "The Avengers assemble once more to undo the damage Thanos has caused to the universe.",
            image = "",
            releaseDate = "2019-04-26",
            status = "Released",
            tagLine = "Avenge the fallen.",
            revenue = "$2.798 billion"
        )
    )
}
