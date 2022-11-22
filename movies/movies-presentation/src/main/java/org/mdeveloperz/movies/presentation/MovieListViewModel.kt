package org.mdeveloperz.movies.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import org.mdeveloperz.common.domain.model.DomainException
import org.mdeveloperz.common.presentation.BaseViewModel
import org.mdeveloperz.common.presentation.DialogCommand
import org.mdeveloperz.common.presentation.LoadingStatus.Loading
import org.mdeveloperz.common.presentation.LoadingStatus.Success
import org.mdeveloperz.common.presentation.LoadingStatus.Error
import org.mdeveloperz.common.presentation.internal.GeneralDomainToPresentationExceptionMapper
import org.mdeveloperz.common.presentation.usecaseexecutor.UseCaseExecutorProvider
import org.mdeveloperz.movies.domain.model.MovieDomainModel
import org.mdeveloperz.movies.domain.usecase.SearchMovieUseCase
import org.mdeveloperz.movies.presentation.mapper.MovieDomainToPresentationMapper
import javax.inject.Inject


@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieDomainToPresentationMapper: MovieDomainToPresentationMapper,
    private val getMoviesUseCase: SearchMovieUseCase,
    generalDomainToPresentationExceptionMapper: GeneralDomainToPresentationExceptionMapper,
    useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<MovieListViewState, DialogCommand>(
    useCaseExecutorProvider = useCaseExecutorProvider,
    exceptionDomainToPresentationMapper = generalDomainToPresentationExceptionMapper
) {
    override fun initialState() = MovieListViewState()


    fun onSearchMoviesAction(keyword: String) {
        updateState { lastState ->
            lastState.copy(
                loadingStatus = Loading
            )
        }
        useCaseExecutor.execute(
            value = keyword,
            useCase = getMoviesUseCase,
            callback = ::onHandleFetchedMovies,
            onError = ::onHandleMovieFetchError
        )
    }

    private fun onHandleMovieFetchError(exception: DomainException) {
        updateState { lastState ->
            lastState.copy(
                loadingStatus = Error,
                movies = emptyList()
            )
        }
    }

    private fun onHandleFetchedMovies(movies: List<MovieDomainModel>) {
        updateState { lastState ->
            lastState.copy(
                loadingStatus = Success,
                movies = movies.map(movieDomainToPresentationMapper::toPresentation)
            )
        }
    }
}