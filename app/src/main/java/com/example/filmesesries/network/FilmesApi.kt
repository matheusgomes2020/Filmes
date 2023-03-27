package com.example.filmesesries.network

import com.example.filmesesries.model.Results
import com.example.filmesesries.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface FilmesApi {
    //https://api.themoviedb.org/3/search/movie?api_key=***&query=Jack+Reacher
    @GET("search/movie?api_key=***&query=Jack+Reacher")
    suspend fun getAllFilmes(

        @Query("query")  query: String

    ): Results


    //https://api.themoviedb.org/3/movie/popular?api_key=***
    @GET("movie/popular?api_key=" + Constants.API_KEY)
    suspend fun getPopularMovies( ) : Results

    //https://api.themoviedb.org/3/movie/550?api_key=***
    //https://api.themoviedb.org/3/

    @GET("movie/{movieID}?api_key=" + Constants.API_KEY)
    suspend fun getFilmeInfo(@Path("movieID") filmeId: String) : com.example.filmesesries.model.Result

}