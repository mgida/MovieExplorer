package com.example.movieexplorer.domain.use_case

import com.example.movieexplorer.data.dto.credits.MovieCreditsResponse
import com.example.movieexplorer.domain.mapper.UICastMapper
import com.example.movieexplorer.domain.mapper.UICrewMapper
import com.example.movieexplorer.domain.model.movie_credits.CastModel
import com.example.movieexplorer.domain.model.movie_credits.CrewModel
import javax.inject.Inject

private const val DEPARTMENT_ACTING = "Acting"
private const val DEPARTMENT_DIRECTING = "Directing"

class ProcessCreditsUseCase @Inject constructor(
    private val uiCastMapper: UICastMapper,
    private val uiCrewMapper: UICrewMapper
) {

    operator fun invoke(movieCreditsResponse: MovieCreditsResponse): Pair<List<CastModel>?, List<CrewModel>?> {
        val castModels = movieCreditsResponse.cast?.map(uiCastMapper::map)
        val crewModels = movieCreditsResponse.crew?.map(uiCrewMapper::map)

        val actors = castModels?.filter { it.knownForDepartment == DEPARTMENT_ACTING }
            ?.sortedByDescending { it.popularity }
            ?.take(5)

        val directors = crewModels?.filter { it.knownForDepartment == DEPARTMENT_DIRECTING }
            ?.sortedByDescending { it.popularity }
            ?.take(5)

        return Pair(actors, directors)
    }
}