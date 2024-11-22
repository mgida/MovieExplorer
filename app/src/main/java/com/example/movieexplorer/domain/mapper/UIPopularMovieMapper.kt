package com.example.movieexplorer.domain.mapper

import com.example.movieexplorer.data.dto.popular_movies.MovieResponse
import com.example.movieexplorer.domain.model.popular_movies.PopularMovieModel
import javax.inject.Inject

class UIPopularMovieMapper @Inject constructor() : IMapper<MovieResponse, PopularMovieModel> {
    override fun map(input: MovieResponse): PopularMovieModel {
        return PopularMovieModel(
            title = input.originalTitle.orEmpty(),
            overview = input.overview.orEmpty(),
            image = input.posterPath.orEmpty(),
            releaseDate = input.releaseDate.orEmpty()
        )
    }
}