package com.example.filmesesries.repository

import com.example.filmesesries.data.Resource
import com.example.filmesesries.network.FilmesApi
import javax.inject.Inject

class FilmesRepository @Inject constructor( private val api: FilmesApi) {

    suspend fun searchMovies(searchQuery: String): Resource<List<com.example.filmesesries.model.ResultX>> {

        return try {
            Resource.Loading( data = true )

            val itemList = api.searchMovies( searchQuery).results
            if (itemList.isNotEmpty()) Resource.Loading(data = false)
            Resource.Success(data = itemList)

        }catch ( exception: Exception ){
            Resource.Error( message = exception.message.toString() )
        }

    }

    suspend fun getPopularMovies(): Resource<List<com.example.filmesesries.model.Result>>{

        return try {
            Resource.Loading(data = true)

            val itemList = api.getPopularMovies().results
            if (itemList.isNotEmpty()) Resource.Loading(data = false)
            Resource.Success(data = itemList)
        }catch (exception: Exception) {
            Resource.Error(message = exception.message.toString())
        }

    }

    suspend fun getMovieInfo( movieID: String) : Resource<com.example.filmesesries.model.Result> {

        val response = try {
            Resource.Loading( data = true )
            api.getMovieInfo( movieID )
        } catch (exception: Exception){
            return Resource.Error(message = "An error occurred ${exception.message.toString()}")
        }
        Resource.Loading(data = false)
        return Resource.Success(data = response)
    }



}