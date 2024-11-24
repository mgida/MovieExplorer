package com.example.movieexplorer.presentation.movie_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.movieexplorer.R
import com.example.movieexplorer.domain.model.movie_details.MovieDetailsModel
import com.example.movieexplorer.util.ThemePreviews

@Composable
fun MainDetailsSection(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetailsModel,
    isInWatchList: Boolean,
    onWatchListToggle: (MovieDetailsModel) -> Unit
) {

    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberAsyncImagePainter(movieDetails.image),
            contentDescription = "",
            modifier = modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)),
            contentScale = ContentScale.Crop
        )

        IconButton(
            onClick = { onWatchListToggle(movieDetails) },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(48.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                    shape = CircleShape
                )
        ) {
            Icon(
                imageVector = if (isInWatchList) Icons.Default.Check else Icons.Default.Add,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }


    Column(modifier = modifier) {
        Text(
            text = movieDetails.title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 16.dp, start = 12.dp, end = 12.dp)
        )

        Text(
            text = movieDetails.tagLine,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )

        Text(
            text = movieDetails.overview,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8F)
            ),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 20.sp
        )

        TextDetailInfo(info = stringResource(R.string.release_date, movieDetails.releaseDate))

        Spacer(modifier = Modifier.height(6.dp))

        TextDetailInfo(info = stringResource(R.string.status, movieDetails.status))

        TextDetailInfo(info = stringResource(R.string.revenue, movieDetails.revenue))
    }

}

@Composable
private fun TextDetailInfo(info: String) {
    Text(
        text = info,
        style = MaterialTheme.typography.bodySmall.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8F)
        ),
        modifier = Modifier.padding(horizontal = 12.dp)
    )
}

@ThemePreviews
@Composable
fun MainDetailsSectionPreview() {
    val sampleMovieDetails = MovieDetailsModel(
        id = 1,
        title = "Avengers: Endgame",
        tagLine = "Part of the journey is the end.",
        overview = "After the devastating events",
        image = "",
        releaseDate = "2019-04-26",
        status = "Released",
        revenue = "$2,798,000,000"
    )

    MainDetailsSection(movieDetails = sampleMovieDetails, isInWatchList = true) {}
}
