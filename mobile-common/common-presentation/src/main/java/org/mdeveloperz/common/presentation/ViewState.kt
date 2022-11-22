package org.mdeveloperz.common.presentation

sealed interface LoadingStatus {
    object Idle: LoadingStatus
    object Loading: LoadingStatus
    object Success: LoadingStatus
    object Error: LoadingStatus
}

interface ViewState
