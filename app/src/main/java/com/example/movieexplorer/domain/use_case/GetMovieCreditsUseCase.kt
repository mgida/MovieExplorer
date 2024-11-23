package com.example.movieexplorer.domain.use_case

import com.example.movieexplorer.data.dto.credits.MovieCreditsResponse
import com.example.movieexplorer.domain.repo.MovieRepo
import com.example.movieexplorer.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieCreditsUseCase @Inject constructor(private val repo: MovieRepo) {
    operator fun invoke(
        movieId: Int,
    ): Flow<Resource<MovieCreditsResponse>> = flow {
        emit(Resource.Loading())
        try {
            val credits = repo.getMovieCredits(movieId)

            emit(Resource.Success(data = credits))

        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage.orEmpty()))
        }
    }
}