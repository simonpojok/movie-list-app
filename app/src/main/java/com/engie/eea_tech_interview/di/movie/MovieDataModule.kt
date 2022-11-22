package com.engie.eea_tech_interview.di.movie

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mdeveloperz.movies.datasource.MoviesDataRepository
import org.mdeveloperz.movies.datasource.datasource.RemoteDataSource
import org.mdeveloperz.movies.datasource.mapper.MovieDataToDomainMapper
import org.mdeveloperz.movies.domain.MovieRepository

@Module
@InstallIn(SingletonComponent::class)
object MovieDataModule {

    @Provides
    fun providesMoviesDataRepository(
        movieDataToDomainMapper: MovieDataToDomainMapper,
        remoteDataSource: RemoteDataSource
    ): MovieRepository = MoviesDataRepository(
        movieDataToDomainMapper = movieDataToDomainMapper,
        remoteDataSource = remoteDataSource
    )

    @Provides
    fun providesMovieDataToDomainMapper() = MovieDataToDomainMapper()
}