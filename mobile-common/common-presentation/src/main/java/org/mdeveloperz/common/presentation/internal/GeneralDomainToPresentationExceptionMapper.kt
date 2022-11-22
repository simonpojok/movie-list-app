package org.mdeveloperz.common.presentation.internal

import org.mdeveloperz.common.presentation.exception.DomainException
import org.mdeveloperz.common.presentation.exception.PresentationException
import org.mdeveloperz.common.presentation.mappers.DomainToPresentationMapper

class GeneralDomainToPresentationExceptionMapper :
    DomainToPresentationMapper<DomainException, PresentationException>() {
    override fun map(input: DomainException): PresentationException {
        TODO("Not yet implemented")
    }
}
