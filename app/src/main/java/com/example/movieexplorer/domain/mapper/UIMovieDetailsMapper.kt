package com.example.movieexplorer.domain.mapper

import com.example.movieexplorer.data.dto.movie_details.MovieDetailsResponse
import com.example.movieexplorer.domain.model.movie_details.MovieDetailsModel
import com.example.movieexplorer.util.IMAGE_URL
import javax.inject.Inject


class UIMovieDetailsMapper @Inject constructor() :
    IMapper<MovieDetailsResponse, MovieDetailsModel> {
    override fun map(input: MovieDetailsResponse): MovieDetailsModel {

        return MovieDetailsModel(
            id = input.id,
            title = input.originalTitle.orEmpty(),
            overview = input.overview.orEmpty(),
            image = "$IMAGE_URL${input.posterPath.orEmpty()}",
            releaseDate = input.releaseDate.orEmpty(),
            revenue = input.revenue.toString(),
            status = input.status.orEmpty(),
            tagLine = input.tagline.orEmpty()
        )
    }
}