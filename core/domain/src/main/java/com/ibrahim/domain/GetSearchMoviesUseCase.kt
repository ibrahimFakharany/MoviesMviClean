package com.ibrahim.domain

import com.ibrahim.domain.repository.IMoviesRepository
import javax.inject.Inject

class GetSearchMoviesUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository,
) {
    operator fun invoke(query: String, page: Int = 1) = moviesRepository.searchMovies(query, page)
}