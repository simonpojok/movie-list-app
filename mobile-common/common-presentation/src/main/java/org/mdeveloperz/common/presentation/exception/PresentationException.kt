package org.mdeveloperz.common.presentation.exception

abstract class PresentationException(val throwable: Throwable = Throwable()) : Exception(throwable)
