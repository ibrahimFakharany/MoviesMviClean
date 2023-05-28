package com.ibrahim.movies_clean_mvi.di

import com.ibrahim.data.MoviesRepository
import com.ibrahim.data.remoteDataRepo.IRemoteDataRepository
import com.ibrahim.domain.repository.IMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideRepository(
        remoteDataRepository: IRemoteDataRepository
    ): IMoviesRepository = MoviesRepository(remoteDataRepository)
}