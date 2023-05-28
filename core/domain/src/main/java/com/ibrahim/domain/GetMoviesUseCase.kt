package com.ibrahim.domain

import com.ibrahim.domain.repository.IMoviesRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository,
) {
    operator fun invoke(page: Int = 1) = moviesRepository.getMovies(page)
}