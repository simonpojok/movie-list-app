package org.mdeveloperz.movie.ui

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import org.mdeveloperz.movie.ui.mapper.MoviePresentationToUiMapper
import org.mdeveloperz.movie.ui.model.MovieUiModel
import org.mdeveloperz.movies.presentation.model.MoviePresentationModel

@RunWith(Parameterized::class)
class MoviePresentationToUiMapperTest(
    private val presentationModel: MoviePresentationModel,
    private val expectedResult: MovieUiModel
) {
    companion object {
        @JvmStatic
        @Parameters(name = "Given domain {0} when toUi returns expected ui model {1}")
        fun parameters() = listOf(
            arrayOf(
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
                MovieUiModel(
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
            ), arrayOf(
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
                ),
                MovieUiModel(
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
        )
    }

    private lateinit var classUnderTest: MoviePresentationToUiMapper

    @Before
    fun setUp() {
        classUnderTest = MoviePresentationToUiMapper()
    }

    @Test
    fun `Given presentation model when toUi return expected ui model`() {
        // When
        val actualResult = classUnderTest.toUi(presentationModel)

        // Then
        assertEquals(expectedResult, actualResult)
    }
}