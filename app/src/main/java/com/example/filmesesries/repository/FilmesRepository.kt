package com.example.filmesesries.repository

import com.example.filmesesries.data.Resource
import com.example.filmesesries.network.FilmesApi
import com.example.filmesesries.utils.Constants
import javax.inject.Inject

class FilmesRepository @Inject constructor( private val api: FilmesApi) {

    suspend fun getFilmes(searchQuery: String): Resource<List<com.example.filmesesries.model.Result>> {

        return try {
            Resource.Loading( data = true )

            val itemList = api.getAllFilmes( searchQuery).results
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
            api.getFilmeInfo( movieID )
        } catch (exception: Exception){
            return Resource.Error(message = "An error occurred ${exception.message.toString()}")
        }
        Resource.Loading(data = false)
        return Resource.Success(data = response)
    }



}