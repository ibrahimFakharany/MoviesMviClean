package com.ibrahim.movies_clean_mvi.di

import com.ibrahim.data.remoteDataRepo.IRemoteDataRepository
import com.ibrahim.remotedatasource.RemoteDataRepositoryImpl
import com.ibrahim.remotedatasource.service.MoviesService
import com.ibrahim.remotedatasource.service.MoviesServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {
    @Provides
    fun provideRemoteDataSource(
        apiService: MoviesService,
    ): IRemoteDataRepository = RemoteDataRepositoryImpl(apiService)


    @Provides
    fun provideApiService(): MoviesService = MoviesServiceFactory.makeMoviesService(true)

}