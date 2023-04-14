package com.example.filmesesries.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.filmesesries.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false )
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDatabaseDao
}