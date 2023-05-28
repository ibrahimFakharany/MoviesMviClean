package com.ibrahim.data

import com.ibrahim.base.AppResponse
import com.ibrahim.base.AppResponseWithGenericError
import com.ibrahim.base.FullMovie
import com.ibrahim.base.GenreResponse
import com.ibrahim.base.Movie
import com.ibrahim.base.MoviesResponse
import com.ibrahim.data.remoteDataRepo.IRemoteDataRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesRepositoryTest {

    val fakeRemoteDataSource = object : IRemoteDataRepository {
        override fun getGenres(): Flow<AppResponseWithGenericError<GenreResponse>> {
            TODO("Not yet implemented")
        }

        override fun getMovies(page: Int): Flow<AppResponseWithGenericError<MoviesResponse>> =
            flow {
                emit(
                    AppResponse.Success(
                        MoviesResponse(
                            page = 0,
                            totalPages = 0,
                            movies = listOf(
                                Movie(
                                    id = 1,
                                    title = "movie",
                                    poster = "",
                                    genreIds = emptyList()
                                )
                            )
                        )
                    )
                )
            }

        override fun getMovieDetails(movieId: Int): Flow<AppResponseWithGenericError<FullMovie>> {
            TODO("Not yet implemented")
        }

        override fun search(query: String): Flow<AppResponseWithGenericError<MoviesResponse>> {
            TODO("Not yet implemented")
        }

    }


    @Test
    fun getMovies() {
        runTest {
            val repo = MoviesRepository(remoteDataSource = fakeRemoteDataSource)
            val data = repo.getMovies(page = 1).first() as AppResponse.Success

            assertEquals(1, data.body.movies.size)
        }
    }
}