package com.example.movieexplorer.domain.mapper

import com.example.movieexplorer.data.dto.credits.Crew
import com.example.movieexplorer.domain.model.movie_credits.CrewModel
import com.example.movieexplorer.util.IMAGE_URL
import javax.inject.Inject

class UICrewMapper @Inject constructor() : IMapper<Crew, CrewModel> {
    override fun map(input: Crew): CrewModel {

        return CrewModel(
            id = input.id,
            name = input.name.orEmpty(),
            job = input.job.orEmpty(),
            popularity = input.popularity ?: 0.0,
            profilePath = "$IMAGE_URL${input.profilePath.orEmpty()}",
            knownForDepartment = input.knownForDepartment.orEmpty()
        )
    }
}