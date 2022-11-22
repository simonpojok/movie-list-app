package org.mdeveloperz.movies.remote.datasource.mapper

import org.mdeveloperz.common.remote.datasource.mapper.mapper.RemoteToDataMapper
import org.mdeveloperz.movies.datasource.model.MovieDataModel
import org.mdeveloperz.movies.remote.datasource.model.MovieRemoteModel

class MovieRemoteToDataMapper: RemoteToDataMapper<MovieRemoteModel, MovieDataModel>() {
    override fun map(input: MovieRemoteModel) = MovieDataModel(
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