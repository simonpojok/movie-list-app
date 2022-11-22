package org.mdeveloperz.common.domain.model

data class UnknownDomainException(override val throwable: Throwable) : DomainException(throwable) {
    constructor(errorMessage: String) : this(Throwable(errorMessage))
}
