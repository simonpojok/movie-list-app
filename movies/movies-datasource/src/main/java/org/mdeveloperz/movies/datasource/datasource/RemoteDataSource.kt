package org.mdeveloperz.movies.datasource.datasource

import org.mdeveloperz.movies.datasource.model.MovieDataModel

interface RemoteDataSource {
    suspend fun searchMovies(keyword: String): List<MovieDataModel>
}