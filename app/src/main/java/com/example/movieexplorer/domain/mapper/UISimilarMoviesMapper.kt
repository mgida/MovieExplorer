package com.example.movieexplorer.domain.mapper

import com.example.movieexplorer.data.dto.similar_movies.SimilarMovieResponse
import com.example.movieexplorer.domain.model.similar_movies.SimilarMovieModel
import javax.inject.Inject

class UISimilarMoviesMapper @Inject constructor(
    private val mapper: UISimilarMovieMapper
) :
    IMapper<List<SimilarMovieResponse>, List<SimilarMovieModel>> {
    override fun map(input: List<SimilarMovieResponse>): List<SimilarMovieModel> {
        val similarMovies = mutableListOf<SimilarMovieModel>()

        for (movieResponse in input) {
            similarMovies.add(
                mapper.map(movieResponse)
            )
        }
        return similarMovies
    }
}