package org.mdeveloperz.movies.remote.datasource

import org.mdeveloperz.movies.remote.datasource.model.GenreRemoteModel
import org.mdeveloperz.movies.remote.datasource.model.MovieRemoteModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesRemoteService {
    @GET("search/movie")
    suspend fun getSearchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
    ): SearchResult

    @GET("genre/movie/list")
    suspend fun getGenre(): GenreResult
}

data class SearchResult(
    val results: List<MovieRemoteModel>
)

data class GenreResult(
    val genres: List<GenreRemoteModel>
)