package com.example.filmesesries.screens.details

import androidx.lifecycle.ViewModel
import com.example.filmesesries.data.Resource
import com.example.filmesesries.repository.FilmesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor( private val repository: FilmesRepository)
    : ViewModel(){

        suspend fun getMovieInfo( movieId: String ): Resource<com.example.filmesesries.model.Result> {

            return repository.getMovieInfo( movieId )

        }
}