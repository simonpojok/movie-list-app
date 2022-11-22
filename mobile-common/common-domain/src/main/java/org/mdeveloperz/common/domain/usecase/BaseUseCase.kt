package org.mdeveloperz.common.domain.usecase

import org.mdeveloperz.common.domain.model.DomainException
import org.mdeveloperz.common.domain.model.UnknownDomainException

interface BaseUseCase<REQUEST, RESULT> {
    suspend fun execute(value: REQUEST, callback: (RESULT) -> Unit)

    fun onError(throwable: Throwable): DomainException = UnknownDomainException(throwable)
}
