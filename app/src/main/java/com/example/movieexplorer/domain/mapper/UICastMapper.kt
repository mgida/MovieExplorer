package com.example.movieexplorer.domain.mapper

import com.example.movieexplorer.data.dto.credits.Cast
import com.example.movieexplorer.domain.model.movie_credits.CastModel
import com.example.movieexplorer.util.IMAGE_URL
import javax.inject.Inject

class UICastMapper @Inject constructor() : IMapper<Cast, CastModel> {
    override fun map(input: Cast): CastModel {

        return CastModel(
            id = input.id,
            name = input.name.orEmpty(),
            character = input.character.orEmpty(),
            popularity = input.popularity ?: 0.0,
            profilePath = "$IMAGE_URL${input.profilePath.orEmpty()}",
            knownForDepartment = input.knownForDepartment.orEmpty()
        )
    }
}