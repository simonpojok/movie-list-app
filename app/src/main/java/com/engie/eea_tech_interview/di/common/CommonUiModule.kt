package com.engie.eea_tech_interview.di.common

import android.content.Context
import android.content.res.Resources
import com.engie.eea_tech_interview.ui.GlobalDestinationMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.mdeveloperz.common.ui.navigation.GlobalDestinationMapper

@Module
@InstallIn(SingletonComponent::class)
object CommonUiModule {
    @Provides
    fun providesGlobalDestinationMapper(): GlobalDestinationMapper = GlobalDestinationMapperImpl()

    @Provides
    fun providesResources(@ApplicationContext appContext: Context): Resources =
        appContext.resources
}