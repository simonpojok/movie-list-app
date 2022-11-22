package com.engie.eea_tech_interview.di.movie

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mdeveloperz.common.domain.usecase.CoroutineContextProvider
import org.mdeveloperz.movies.domain.MovieRepository
import org.mdeveloperz.movies.domain.usecase.SearchMovieUseCase
import org.mdeveloperz.movies.domain.usecase.GetMoviesUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
object MovieDomainModule {

    @Provides
    fun providesGetMoviesUseCase(
        movieRepository: MovieRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): SearchMovieUseCase = GetMoviesUseCaseImpl(
        movieRepository = movieRepository,
        coroutineContextProvider = coroutineContextProvider
    )
}