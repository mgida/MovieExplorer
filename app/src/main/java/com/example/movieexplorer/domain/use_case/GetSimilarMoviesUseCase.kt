package com.example.movieexplorer.domain.use_case

import com.example.movieexplorer.data.dto.similar_movies.SimilarMoviesResponse
import com.example.movieexplorer.domain.repo.MovieRepo
import com.example.movieexplorer.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSimilarMoviesUseCase @Inject constructor(private val repo: MovieRepo) {
    operator fun invoke(
        movieId: Int,
    ): Flow<Resource<SimilarMoviesResponse>> = flow {
        emit(Resource.Loading())
        try {
            val similarMovies = repo.getSimilarMovies(movieId)

            emit(Resource.Success(data = similarMovies))

        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage.orEmpty()))
        }
    }
}