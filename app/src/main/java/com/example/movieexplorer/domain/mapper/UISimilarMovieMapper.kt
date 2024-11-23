package com.example.movieexplorer.domain.mapper

import com.example.movieexplorer.data.dto.similar_movies.SimilarMovieResponse
import com.example.movieexplorer.domain.model.similar_movies.SimilarMovieModel
import com.example.movieexplorer.util.IMAGE_URL
import javax.inject.Inject

class UISimilarMovieMapper @Inject constructor() :
    IMapper<SimilarMovieResponse, SimilarMovieModel> {
    override fun map(input: SimilarMovieResponse): SimilarMovieModel {

        return SimilarMovieModel(
            id = input.id,
            title = input.originalTitle.orEmpty(),
            overview = input.overview.orEmpty(),
            image = "${IMAGE_URL}${input.posterPath.orEmpty()}"
        )
    }
}