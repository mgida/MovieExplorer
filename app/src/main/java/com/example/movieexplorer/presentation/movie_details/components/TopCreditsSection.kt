package com.example.movieexplorer.presentation.movie_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieexplorer.domain.model.movie_credits.CastModel
import com.example.movieexplorer.domain.model.movie_credits.CrewModel
import com.example.movieexplorer.ui.theme.MovieExplorerTheme
import com.example.movieexplorer.util.ThemePreviews


@Composable
fun TopCreditsSection(
    actors: List<CastModel>,
    directors: List<CrewModel>
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Top Actors",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
            actors.forEach { actor ->
                CreditItem(
                    name = actor.name,
                    role = actor.character,
                    profilePath = actor.profilePath
                )
            }
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Top Directors",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
            directors.forEach { director ->
                CreditItem(
                    name = director.name,
                    role = director.job,
                    profilePath = director.profilePath
                )
            }
        }
    }
}


@ThemePreviews
@Composable
fun TopCreditsSectionPreview() {
    val sampleActors = listOf(
        CastModel(
            id = 1,
            name = "Robert Downey Jr.",
            character = "Tony Stark / Iron Man",
            popularity = 90.0,
            profilePath = "",
            knownForDepartment = "Acting"
        ),
        CastModel(
            id = 2,
            name = "Chris Hemsworth",
            character = "Thor",
            popularity = 85.0,
            profilePath = "",
            knownForDepartment = "Acting"
        )
    )

    val sampleDirectors = listOf(
        CrewModel(
            id = 1,
            name = "Jon Favreau",
            job = "Director",
            popularity = 80.0,
            profilePath = "",
            knownForDepartment = "Directing"
        ),
        CrewModel(
            id = 2,
            name = "Joss Whedon",
            job = "Director",
            popularity = 78.0,
            profilePath = "",
            knownForDepartment = "Directing"
        )
    )

    MovieExplorerTheme {
        TopCreditsSection(
            actors = sampleActors,
            directors = sampleDirectors
        )
    }
}
