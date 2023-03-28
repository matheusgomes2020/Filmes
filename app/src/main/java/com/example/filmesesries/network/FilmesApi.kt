package com.example.filmesesries.network

import com.example.filmesesries.model.Results
import com.example.filmesesries.model.Results2
import com.example.filmesesries.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface FilmesApi {
    //https://api.themoviedb.org/3/search/movie?api_key=0f5183b12ca04341d5f0f71d8bc698b5&query=Jack+Reacher
    @GET("search/movie?api_key=" + Constants.API_KEY)
    suspend fun searchMovies(

        @Query("query")  query: String

    ): Results2


    //https://api.themoviedb.org/3/movie/popular?api_key=0f5183b12ca04341d5f0f71d8bc698b5
    @GET("movie/popular?api_key=" + Constants.API_KEY)
    suspend fun getPopularMovies( ) : Results

    //https://api.themoviedb.org/3/movie/550?api_key=0f5183b12ca04341d5f0f71d8bc698b5
    //https://api.themoviedb.org/3/

    @GET("movie/{movieID}?api_key=" + Constants.API_KEY)
    suspend fun getMovieInfo(@Path("movieID") filmeId: String) : com.example.filmesesries.model.Result

}