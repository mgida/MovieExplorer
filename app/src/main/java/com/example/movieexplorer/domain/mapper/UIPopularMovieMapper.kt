package com.example.movieexplorer.domain.mapper

import com.example.movieexplorer.data.dto.popular_movies.MovieResponse
import com.example.movieexplorer.domain.model.popular_movies.PopularMovieModel
import timber.log.Timber
import javax.inject.Inject

private const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

class UIPopularMovieMapper @Inject constructor() : IMapper<MovieResponse, PopularMovieModel> {
    override fun map(input: MovieResponse): PopularMovieModel {

        Timber.d("Movies path ${input.posterPath}")
        return PopularMovieModel(
            title = input.originalTitle.orEmpty(),
            overview = input.overview.orEmpty(),
            image = "${IMAGE_URL}${input.posterPath.orEmpty()}",
            releaseDate = input.releaseDate.orEmpty()
        )
    }
}