package com.example.filmesesries.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*

@Entity(tableName = "movies_tbl")
data class Movie(

    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "movie_title")
    val title: String,

    @ColumnInfo(name = "movie_genres")
    val genre_ids: List<Int>,

    @ColumnInfo(name = "movie_overview")
    val overview: String,

    @ColumnInfo(name = "movie_popularity")
    val popularity: Double,

    @ColumnInfo(name = "movie_poster_path")
    val poster_path: String,

    @ColumnInfo(name = "movie_release_date")
    val release_date: String,


    @ColumnInfo(name = "movie_vote_average")
    val vote_average: Double,

    @ColumnInfo(name = "movie_vote_count")
    val vote_count: Int,

    @ColumnInfo(name = "movie_entry_date")
    val entryDate: Date = Date.from(Instant.now())



)
