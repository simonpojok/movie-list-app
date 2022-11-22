package com.engie.eea_tech_interview.di.common

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mdeveloperz.common.presentation.internal.GeneralDomainToPresentationExceptionMapper

@Module
@InstallIn(SingletonComponent::class)
object CommonPresentationModule {
    @Provides
    fun providesGeneralDomainToPresentationExceptionMapper() =
        GeneralDomainToPresentationExceptionMapper()
}