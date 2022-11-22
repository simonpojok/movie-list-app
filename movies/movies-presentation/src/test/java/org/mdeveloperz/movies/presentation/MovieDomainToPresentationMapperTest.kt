package org.mdeveloperz.movies.presentation

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import org.mdeveloperz.movies.domain.model.MovieDomainModel
import org.mdeveloperz.movies.presentation.mapper.MovieDomainToPresentationMapper
import org.mdeveloperz.movies.presentation.model.MoviePresentationModel

@RunWith(Parameterized::class)
class MovieDomainToPresentationMapperTest(
    private val domainModel: MovieDomainModel,
    private val expectedResult: MoviePresentationModel
) {
    companion object {
        @JvmStatic
        @Parameters(name = "Given domain {0} when toPresentation returns expected presentation model {1}")
        fun parameters() = listOf(
            arrayOf(
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
            ), arrayOf(
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
        )
    }

    private lateinit var classUnderTest: MovieDomainToPresentationMapper

    @Before
    fun setUp() {
        classUnderTest = MovieDomainToPresentationMapper()
    }

    @Test
    fun `Given domain model when toPresentation return expected presentation model`() {
        // When
        val actualResult = classUnderTest.toPresentation(domainModel)

        // Then
        assertEquals(expectedResult, actualResult)
    }
}