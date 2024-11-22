package com.example.movieexplorer.domain.mapper

import com.example.movieexplorer.data.dto.popular_movies.MovieResponse
import com.example.movieexplorer.domain.model.popular_movies.PopularMovieModel
import javax.inject.Inject

class UIPopularMoviesMapper @Inject constructor() :
    IMapper<List<MovieResponse>, List<PopularMovieModel>> {
    override fun map(input: List<MovieResponse>): List<PopularMovieModel> {
        val popularMovies = mutableListOf<PopularMovieModel>()
        for (movieResponse in input) {
            popularMovies.add(
                PopularMovieModel(
                    title = movieResponse.originalTitle.orEmpty(),
                    overview = movieResponse.overview.orEmpty(),
                    image = movieResponse.posterPath.orEmpty(),
                    releaseDate = movieResponse.releaseDate.orEmpty(),
                )
            )
        }
        return popularMovies
    }
}