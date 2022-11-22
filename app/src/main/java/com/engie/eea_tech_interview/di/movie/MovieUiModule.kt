package com.engie.eea_tech_interview.di.movie

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mdeveloperz.common.ui.navigation.GlobalDestinationMapper
import org.mdeveloperz.movie.ui.mapper.MoviePresentationToUiMapper
import org.mdeveloperz.movie.ui.movies.MovieListUiDestinationMapper

@Module
@InstallIn(SingletonComponent::class)
object MovieUiModule {

    @Provides
    fun providesMovieListUiDestinationMapper(
        globalDestinationMapper: GlobalDestinationMapper
    ) = MovieListUiDestinationMapper(globalDestinationMapper)

    @Provides
    fun providesMoviePresentationToUiMapper() = MoviePresentationToUiMapper()
}