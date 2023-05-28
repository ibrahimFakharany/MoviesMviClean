package com.ibrahim.domain

import com.ibrahim.domain.repository.IMoviesRepository
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository,
) {
    operator fun invoke() = moviesRepository.getGenres()
}