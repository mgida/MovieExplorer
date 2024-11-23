package com.example.movieexplorer.domain.mapper

import com.example.movieexplorer.data.dto.popular_movies.MovieResponse
import com.example.movieexplorer.domain.model.popular_movies.PopularMovieModel
import javax.inject.Inject

class UIPopularMoviesMapper @Inject constructor(
    private val uiPopularMovieMapper: UIPopularMovieMapper
) :
    IMapper<List<MovieResponse>, List<PopularMovieModel>> {
    override fun map(input: List<MovieResponse>): List<PopularMovieModel> {
        val popularMovies = mutableListOf<PopularMovieModel>()
        for (movieResponse in input) {
            popularMovies.add(
                uiPopularMovieMapper.map(movieResponse)
            )
        }
        return popularMovies
    }
}