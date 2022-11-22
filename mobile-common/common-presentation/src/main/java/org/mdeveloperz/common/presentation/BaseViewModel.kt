package org.mdeveloperz.common.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import org.mdeveloperz.common.domain.usecase.UseCaseExecutor
import org.mdeveloperz.common.presentation.exception.DomainException
import org.mdeveloperz.common.presentation.exception.PresentationException
import org.mdeveloperz.common.presentation.mappers.DomainToPresentationMapper
import org.mdeveloperz.common.presentation.usecaseexecutor.UseCaseExecutorProvider

abstract class BaseViewModel<VIEW_STATE : ViewState, DIALOG_COMMAND : DialogCommand>(
    useCaseExecutorProvider: UseCaseExecutorProvider,
    private val exceptionDomainToPresentationMapper: DomainToPresentationMapper<in DomainException, out PresentationException>
) : ViewModel() {
    val navigationCommands = SingleLiveEvent<PresentationDestination>()
    val snackBarEvents = SingleLiveEvent<Int>()
    val snackBarStringEvents = SingleLiveEvent<String>()

    val presentationExceptionEvents = SingleLiveEvent<PresentationException>()
    private val _viewState = MutableLiveData<VIEW_STATE>().apply { value = initialState() }
    val viewState: LiveData<VIEW_STATE>
        get() = _viewState

    val dialogEvents = SingleLiveEvent<DIALOG_COMMAND>()
    val dialogLoadingState = MutableLiveData(false)
    val useCaseExecutor: UseCaseExecutor = useCaseExecutorProvider(viewModelScope)
    val globalUseCaseExecutor: UseCaseExecutor = useCaseExecutorProvider(GlobalScope)

    fun notify(dialogCommand: DIALOG_COMMAND) {
        dialogEvents.value = dialogCommand
    }

    fun currentViewState() = viewState.value ?: initialState()

    open fun onFragmentCreate() = Unit
    open fun onFragmentViewCreated() = Unit
    open fun onFragmentResume() = Unit
    open fun onFragmentDestroyView() = Unit
    open fun onFragmentPause() = Unit
    open fun onFragmentStop() = Unit
    open fun onFragmentStart() = Unit

    abstract fun initialState(): VIEW_STATE

    protected fun navigate(presentationDestination: PresentationDestination) {
        navigationCommands.value = presentationDestination
    }

    fun updateState(newViewState: VIEW_STATE) {
        _viewState.value = newViewState
    }

    fun updateState(updatedState: (lastState: VIEW_STATE) -> VIEW_STATE) =
        updateState(updatedState(currentViewState()))

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel("Scope cleared")
    }

    protected fun setLoading(state: Boolean) {
        dialogLoadingState.value = state
    }
}
