package org.mdeveloperz.movies.remote.datasource

import org.mdeveloperz.common.remote.datasource.MOVIE_API_KEY
import org.mdeveloperz.movies.datasource.datasource.RemoteDataSource
import org.mdeveloperz.movies.datasource.model.MovieDataModel
import org.mdeveloperz.movies.remote.datasource.mapper.MovieRemoteToDataMapper

class MoviesRemoteDataSource(
    private val movieRemoteToDataMapper: MovieRemoteToDataMapper,
    private val moviesRemoteService: MoviesRemoteService
) : RemoteDataSource {
    override suspend fun searchMovies(keyword: String) = moviesRemoteService.getSearchMovies(
        apiKey = MOVIE_API_KEY,
        query = keyword
    ).results
        .map(movieRemoteToDataMapper::toData)
}