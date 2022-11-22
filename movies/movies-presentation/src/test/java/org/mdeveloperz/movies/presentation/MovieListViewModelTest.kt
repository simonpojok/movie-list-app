package org.mdeveloperz.movies.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mdeveloperz.common.domain.usecase.UseCaseExecutor
import org.mdeveloperz.common.presentation.internal.GeneralDomainToPresentationExceptionMapper
import org.mdeveloperz.common.test.givenSuccessfulUseCaseExecution
import org.mdeveloperz.movies.domain.model.MovieDomainModel
import org.mdeveloperz.movies.domain.usecase.SearchMovieUseCase
import org.mdeveloperz.movies.presentation.mapper.MovieDomainToPresentationMapper
import org.mdeveloperz.movies.presentation.model.MoviePresentationModel
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.given

val moviesDomain = listOf(
    MovieDomainModel(
        id = 1,
        posterPath = "www.google.com/postor",
        overview = "New Movie",
        releaseDate = "23/09/2022",
        originalTitle = "Cool Movie",
        genreIds = emptyList(),
        mediaType = "mp3",
        originalLanguage = "English",
        title = "New Movie 2",
        voteCount = 10,
        hasVideo = false,
    ),
    MovieDomainModel(
        id = 2,
        posterPath = "www.google.com/postor",
        overview = "New Movie 44",
        releaseDate = "23/09/2022",
        originalTitle = "Cool Movie",
        genreIds = emptyList(),
        mediaType = "mp3",
        originalLanguage = "English",
        title = "New Movie 2",
        voteCount = 10,
        hasVideo = false,
    )
)

val moviesPresentation = listOf(
    MoviePresentationModel(
        id = 1,
        posterPath = "www.google.com/postor",
        overview = "New Movie",
        releaseDate = "23/09/2022",
        originalTitle = "Cool Movie",
        genreIds = emptyList(),
        mediaType = "mp3",
        originalLanguage = "English",
        title = "New Movie 2",
        voteCount = 10,
        hasVideo = false,
    ),
    MoviePresentationModel(
        id = 2,
        posterPath = "www.google.com/postor",
        overview = "New Movie 44",
        releaseDate = "23/09/2022",
        originalTitle = "Cool Movie",
        genreIds = emptyList(),
        mediaType = "mp3",
        originalLanguage = "English",
        title = "New Movie 2",
        voteCount = 10,
        hasVideo = false,
    )
)

@RunWith(MockitoJUnitRunner::class)
class MovieListViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var classUnderTest: MovieListViewModel

    @Mock
    private lateinit var useCaseExecutor: UseCaseExecutor

    @Mock
    private lateinit var generalDomainToPresentationExceptionMapper: GeneralDomainToPresentationExceptionMapper

    @Mock
    private lateinit var movieDomainToPresentationMapper: MovieDomainToPresentationMapper

    @Mock
    private lateinit var getMoviesUseCase: SearchMovieUseCase

    @Before
    fun setUp() {
        classUnderTest = MovieListViewModel(
            movieDomainToPresentationMapper = movieDomainToPresentationMapper,
            getMoviesUseCase = getMoviesUseCase,
            generalDomainToPresentationExceptionMapper = generalDomainToPresentationExceptionMapper,
            useCaseExecutorProvider = { useCaseExecutor }
        )
    }

    @Test
    fun `Given Search Keyword When on onSearchMoviesAction Then Returns Movies List`() {
        given(generalDomainToPresentationExceptionMapper.toPresentation(any()))
            .willReturn(any())

        moviesDomain.forEach { movie ->
            given(movieDomainToPresentationMapper.toPresentation(movie))
                .willReturn(moviesPresentation.find { presentationMovie -> presentationMovie.id == movie.id })
        }

        useCaseExecutor.givenSuccessfulUseCaseExecution(
            useCase = getMoviesUseCase,
            input = "Action",
            result = moviesDomain
        )


        // When
        classUnderTest.onSearchMoviesAction("Action")
        val actualResult = classUnderTest.currentViewState()

        assertEquals(moviesPresentation, actualResult)
    }
}