package com.engie.eea_tech_interview.di.movie

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.mdeveloperz.movies.datasource.datasource.RemoteDataSource
import org.mdeveloperz.movies.remote.datasource.MoviesRemoteDataSource
import org.mdeveloperz.movies.remote.datasource.MoviesRemoteService
import org.mdeveloperz.movies.remote.datasource.mapper.MovieRemoteToDataMapper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val CACHE_SIZE: Long = 10 * 1024 * 1024 // 10MB
const val READ_TIME_OUT: Long = 30
const val WRITE_TIME_OUT: Long = 10
const val CONNECT_TIME_OUT: Long = 10

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    const val BASE_URL = "https://api.themoviedb.org/3/"

    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesClient(@ApplicationContext context: Context) = OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        .cache(Cache(context.cacheDir, CACHE_SIZE))
        .build()

    @Provides
    fun providesMoviesRemoteService(retrofit: Retrofit): MoviesRemoteService =
        retrofit.create(MoviesRemoteService::class.java)

    @Provides
    fun providesMovieRemoteToDataMapper() = MovieRemoteToDataMapper()

    @Provides
    fun providesMoviesRemoteDataSource(
        movieRemoteToDataMapper: MovieRemoteToDataMapper,
        moviesRemoteService: MoviesRemoteService
    ) = MoviesRemoteDataSource(
        movieRemoteToDataMapper = movieRemoteToDataMapper,
        moviesRemoteService = moviesRemoteService
    )

    @Provides
    fun providesRemoteDataSource(
        movieRemoteToDataMapper: MovieRemoteToDataMapper,
        moviesRemoteService: MoviesRemoteService
    ): RemoteDataSource = MoviesRemoteDataSource(
        movieRemoteToDataMapper = movieRemoteToDataMapper,
        moviesRemoteService = moviesRemoteService
    )
}