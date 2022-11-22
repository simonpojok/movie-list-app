package org.mdeveloperz.movies.presentation

import org.mdeveloperz.common.presentation.LoadingStatus
import org.mdeveloperz.common.presentation.LoadingStatus.Idle
import org.mdeveloperz.common.presentation.ViewState
import org.mdeveloperz.movies.presentation.model.MoviePresentationModel

data class MovieListViewState(
    val loadingStatus: LoadingStatus = Idle,
    val movies: List<MoviePresentationModel> = emptyList()
) : ViewState