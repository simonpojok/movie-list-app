package org.mdeveloperz.movies.datasource.mapper

import org.mdeveloperz.common.datasource.DataToDomainMapper
import org.mdeveloperz.movies.datasource.model.MovieDataModel
import org.mdeveloperz.movies.domain.model.MovieDomainModel

class MovieDataToDomainMapper : DataToDomainMapper<MovieDataModel, MovieDomainModel>() {
    override fun map(input: MovieDataModel) = MovieDomainModel(
        id = input.id,
        posterPath = input.posterPath,
        overview = input.overview,
        releaseDate = input.releaseDate,
        originalTitle = input.originalTitle,
        genreIds = input.genreIds,
        mediaType = input.mediaType,
        originalLanguage = input.originalLanguage,
        title = input.title,
        voteCount = input.voteCount,
        hasVideo = input.hasVideo
    )
}