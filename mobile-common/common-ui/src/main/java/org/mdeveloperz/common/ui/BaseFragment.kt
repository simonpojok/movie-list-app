package org.mdeveloperz.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import org.mdeveloperz.common.presentation.BaseViewModel
import org.mdeveloperz.common.presentation.DialogCommand
import org.mdeveloperz.common.presentation.PresentationDestination
import org.mdeveloperz.common.presentation.ViewState
import org.mdeveloperz.common.ui.navigation.UiDestinationMapper

const val LOADING_INDICATOR_REQUEST_KEY = "LOADING_INDICATOR_REQUEST_KEY"
const val LOADING_INDICATOR_BUNDLE_KEY = "LOADING_INDICATOR_BUNDLE_KEY"

abstract class BaseFragment<VIEW_STATE : ViewState, DIALOG_COMMAND : DialogCommand> :
    Fragment() {
    abstract val layout: Int
    abstract val destinationMapper: UiDestinationMapper
    abstract val viewModel: BaseViewModel<VIEW_STATE, DIALOG_COMMAND>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    open fun renderViewState(viewState: VIEW_STATE) = Unit

    open fun renderDialog(dialogCommand: DIALOG_COMMAND) = Unit

    open fun onFragmentResumed() {
        viewModel.onFragmentResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onFragmentCreate()
    }

    @CallSuper
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelInternal()
        viewModel.onFragmentViewCreated()
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        viewModel.onFragmentPause()
    }

    private fun observeViewModelInternal() {
        viewModel.navigationCommands.observe(
            viewLifecycleOwner,
            NavigationObserver(destinationMapper, findNavController())
        )
        viewModel.viewState.observe(viewLifecycleOwner, RenderStateObserver())
        viewModel.dialogEvents.observe(viewLifecycleOwner, DialogEventsObserver())
        viewModel.dialogLoadingState.observe(viewLifecycleOwner, LoadingStateObserver(this))
    }

    override fun onStart() {
        viewModel.onFragmentStart()
        super.onStart()
    }

    override fun onStop() {
        viewModel.onFragmentStop()
        super.onStop()
    }

    override fun onDestroyView() {
        viewModel.onFragmentDestroyView()
        super.onDestroyView()
    }

    private class NavigationObserver(
        private val destinationMapper: UiDestinationMapper,
        private val navController: NavController
    ) : Observer<PresentationDestination> {
        override fun onChanged(presentationDestination: PresentationDestination) {
            try {
                destinationMapper.map(presentationDestination).navigate(navController)
            } catch (exception: IllegalArgumentException) {
            }
        }
    }

    inner class RenderStateObserver : Observer<VIEW_STATE> {
        override fun onChanged(viewState: VIEW_STATE) = renderViewState(viewState)
    }

    inner class DialogEventsObserver : Observer<DIALOG_COMMAND> {
        override fun onChanged(dialogCommand: DIALOG_COMMAND) = renderDialog(dialogCommand)
    }

    internal class LoadingStateObserver(private val baseFragment: Fragment) : Observer<Boolean> {
        override fun onChanged(t: Boolean) {
            baseFragment.setFragmentResult(
                LOADING_INDICATOR_REQUEST_KEY,
                bundleOf(LOADING_INDICATOR_BUNDLE_KEY to t)
            )
        }
    }
}
