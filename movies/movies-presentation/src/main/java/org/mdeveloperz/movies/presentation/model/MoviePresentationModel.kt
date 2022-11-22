package org.mdeveloperz.movies.presentation.model

data class MoviePresentationModel(
    val id: Int,
    val posterPath: String?,
    val overview: String?,
    val releaseDate: String?,
    val originalTitle: String?,
    val genreIds: List<Int>?,
    val mediaType: String?,
    val originalLanguage: String?,
    val title: String?,
    val voteCount: Int?,
    val hasVideo: Boolean?,
)