package com.engie.eea_tech_interview.di.movie

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mdeveloperz.movies.presentation.mapper.MovieDomainToPresentationMapper

@Module
@InstallIn(SingletonComponent::class)
object MoviePresentationModule {
    @Provides
    fun providesMovieDomainToPresentationMapper() = MovieDomainToPresentationMapper()
}