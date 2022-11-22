package org.mdeveloperz.movies.presentation.mapper

import org.mdeveloperz.common.presentation.mappers.DomainToPresentationMapper
import org.mdeveloperz.movies.domain.model.MovieDomainModel
import org.mdeveloperz.movies.presentation.model.MoviePresentationModel

class MovieDomainToPresentationMapper :
    DomainToPresentationMapper<MovieDomainModel, MoviePresentationModel>() {
    override fun map(input: MovieDomainModel) = MoviePresentationModel(
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