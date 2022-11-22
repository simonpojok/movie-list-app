package org.mdeveloperz.movies.domain.usecase

import kotlinx.coroutines.CoroutineScope
import org.mdeveloperz.common.domain.usecase.BackgroundExecuteUseCase
import org.mdeveloperz.common.domain.usecase.BaseUseCase
import org.mdeveloperz.common.domain.usecase.CoroutineContextProvider
import org.mdeveloperz.movies.domain.MovieRepository
import org.mdeveloperz.movies.domain.model.MovieDomainModel

interface SearchMovieUseCase : BaseUseCase<String, List<MovieDomainModel>>

class GetMoviesUseCaseImpl(
    private val movieRepository: MovieRepository,
    coroutineContextProvider: CoroutineContextProvider,
) : SearchMovieUseCase,
    BackgroundExecuteUseCase<String, List<MovieDomainModel>>(coroutineContextProvider) {

    override suspend fun executeInBackground(
        request: String,
        coroutineScope: CoroutineScope
    ) = movieRepository.searchMovies(request)
}