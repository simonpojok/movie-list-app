package org.mdeveloperz.common.presentation.usecaseexecutor

import kotlinx.coroutines.CoroutineScope
import org.mdeveloperz.common.domain.usecase.UseCaseExecutor

typealias UseCaseExecutorProvider = @JvmSuppressWildcards (coroutineScope: CoroutineScope) -> UseCaseExecutor
