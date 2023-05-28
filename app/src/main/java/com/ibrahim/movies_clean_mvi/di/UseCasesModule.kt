package com.ibrahim.movies_clean_mvi.di

import com.ibrahim.domain.GetGenresUseCase
import com.ibrahim.domain.repository.IMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {
    @Provides
    fun provideGetGenresUseCase(moviesRepository: IMoviesRepository): GetGenresUseCase =
        GetGenresUseCase(moviesRepository)

}