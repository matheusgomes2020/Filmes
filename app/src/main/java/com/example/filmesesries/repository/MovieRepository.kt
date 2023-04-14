package com.example.filmesesries.repository

import com.example.filmesesries.data.MovieDatabaseDao
import com.example.filmesesries.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieDatabaseDao: MovieDatabaseDao) {

    suspend fun addMovie( movie: Movie ) = movieDatabaseDao.insert( movie )
    suspend fun updateMovie( movie: Movie ) = movieDatabaseDao.update( movie )
    suspend fun deleteMovie( movie: Movie ) = movieDatabaseDao.deleteMovie( movie )
    suspend fun deleteAllMovies() = movieDatabaseDao.deleteAll()
    suspend fun getAllMovies(): Flow<List<Movie>> = movieDatabaseDao.getMovies().flowOn(Dispatchers.IO)
        .conflate()

}