package org.mdeveloperz.movie.ui.mapper

import org.mdeveloperz.common.ui.mapper.PresentationToUiMapper
import org.mdeveloperz.movie.ui.model.MovieUiModel
import org.mdeveloperz.movies.presentation.model.MoviePresentationModel

class MoviePresentationToUiMapper: PresentationToUiMapper<MoviePresentationModel, MovieUiModel>() {
    override fun map(input: MoviePresentationModel) = MovieUiModel(
        id = input.id,
        posterPath = input.posterPath,
        overview = input.overview,
        releaseDate = input.releaseDate,
        originalLanguage = input.originalLanguage,
        genreIds = input.genreIds,
        mediaType = input.mediaType,
        originalTitle = input.originalTitle,
        title = input.title,
        voteCount = input.voteCount,
        hasVideo = input.hasVideo
    )
}