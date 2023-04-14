package com.example.filmesesries.data

import androidx.room.*
import com.example.filmesesries.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDatabaseDao {

    @Query("SELECT * from movies_tbl")
    fun getMovies():
            Flow<List<Movie>>

    @Query("SELECT * from movies_tbl where id =:id")
    suspend fun getMovieById(id: String): Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(movie: Movie)

    @Query("DELETE from movies_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteMovie(movie: Movie)

}