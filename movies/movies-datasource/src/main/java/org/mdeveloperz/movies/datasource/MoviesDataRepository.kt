package org.mdeveloperz.movies.datasource

import org.mdeveloperz.movies.datasource.datasource.RemoteDataSource
import org.mdeveloperz.movies.datasource.mapper.MovieDataToDomainMapper
import org.mdeveloperz.movies.domain.MovieRepository

class MoviesDataRepository(
    private val movieDataToDomainMapper: MovieDataToDomainMapper,
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {
    override suspend fun searchMovies(keyword: String) = remoteDataSource.searchMovies(
        keyword
    ).map(movieDataToDomainMapper::toDomain)
}