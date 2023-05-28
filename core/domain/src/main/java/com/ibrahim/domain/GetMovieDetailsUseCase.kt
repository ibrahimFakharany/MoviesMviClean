package com.ibrahim.domain

import com.ibrahim.domain.repository.IMoviesRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository,
) {
    operator fun invoke(movieId: Int) = moviesRepository.getMovieDetails(movieId)
}