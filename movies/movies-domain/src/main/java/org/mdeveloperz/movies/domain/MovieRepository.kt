package org.mdeveloperz.movies.domain

import org.mdeveloperz.movies.domain.model.MovieDomainModel

interface MovieRepository {
    suspend fun searchMovies(keyword: String): List<MovieDomainModel>
}