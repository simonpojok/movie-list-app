package org.mdeveloperz.common.test

import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.mdeveloperz.common.domain.model.DomainException
import org.mdeveloperz.common.domain.usecase.BaseUseCase
import org.mdeveloperz.common.domain.usecase.RunningExecution
import org.mdeveloperz.common.domain.usecase.UseCaseExecutor
import org.mdeveloperz.common.presentation.exception.PresentationException
import org.mdeveloperz.common.presentation.internal.GeneralDomainToPresentationExceptionMapper
import org.mockito.BDDMockito.willAnswer

fun <REQUEST, RESULT> UseCaseExecutor.givenSuccessfulUseCaseExecution(
    useCase: BaseUseCase<REQUEST, RESULT>,
    input: REQUEST,
    result: RESULT
) {
    doAnswer { invocationOnMock ->
        (invocationOnMock.arguments[2] as (RESULT) -> Unit)(result)
        mock<RunningExecution>()
    }.whenever(this).execute(
        useCase = eq(useCase),
        value = eq(input),
        callback = any(),
        onError = any()
    )
}

fun <REQUEST> UseCaseExecutor.givenFailedUseCaseExecution(
    useCase: BaseUseCase<REQUEST, *>,
    input: REQUEST,
    domainException: DomainException
) {
    val useCaseExecutor = this
    runBlocking {
        willAnswer { invocation ->
            (invocation.getArgument(3) as (DomainException) -> Unit)(domainException)
            mock<RunningExecution>()
        }.given(useCaseExecutor)
            .execute(
                useCase = eq(useCase),
                value = eq(input),
                callback = any(),
                onError = any()
            )
    }
}

fun <REQUEST> UseCaseExecutor.givenSuccessfulNoResultUseCaseExecution(
    useCase: BaseUseCase<REQUEST, Unit>,
    input: REQUEST
) {
    givenSuccessfulUseCaseExecution(useCase, input, Unit)
}

fun <RESULT> UseCaseExecutor.givenSuccessfulNoArgumentUseCaseExecution(
    useCase: BaseUseCase<Unit, RESULT>,
    result: RESULT
) {
    doAnswer { invocationOnMock ->
        (invocationOnMock.arguments[1] as (RESULT) -> Unit)(result)
        mock<RunningExecution>()
    }.whenever(this).execute(
        useCase = eq(useCase),
        callback = any(),
        onError = any()
    )
}
