package com.example.movieexplorer.domain.use_case

import com.example.movieexplorer.domain.model.movie_details.MovieDetailsModel
import com.example.movieexplorer.domain.repo.MovieRepo
import javax.inject.Inject

class DeleteMovieUseCase @Inject constructor(private val movieRepo: MovieRepo) {
    suspend operator fun invoke(movie: MovieDetailsModel) {
        movieRepo.deleteMovie(movie = movie)
    }

}