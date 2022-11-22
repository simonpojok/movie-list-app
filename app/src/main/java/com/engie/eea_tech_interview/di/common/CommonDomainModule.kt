package com.engie.eea_tech_interview.di.common

import com.swensonhe.quickupdate.common.domain.DispatchersCoroutineContextProvider
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mdeveloperz.common.domain.usecase.CoroutineContextProvider
import org.mdeveloperz.common.domain.usecase.UseCaseExecutor
import org.mdeveloperz.common.presentation.usecaseexecutor.UseCaseExecutorProvider

@Module
@InstallIn(SingletonComponent::class)
object CommonDomainModule {

    @Provides
    @Reusable
    fun provideCoroutineContextProvider(): CoroutineContextProvider =
        DispatchersCoroutineContextProvider()

    @Provides
    @Reusable
    fun providesUseCaseExecutorProvider(): UseCaseExecutorProvider =
        { coroutineScope -> UseCaseExecutor(coroutineScope) }
}