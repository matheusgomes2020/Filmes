package com.example.filmesesries.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmesesries.data.Resource
import com.example.filmesesries.repository.FilmesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor( private val repository: FilmesRepository)
    : ViewModel(){

    var list: List<com.example.filmesesries.model.Result> by mutableStateOf(listOf())
    var list2: List<com.example.filmesesries.model.ResultX> by mutableStateOf(listOf())
    var isLoading: Boolean by mutableStateOf(true)
    var isLoading2: Boolean by mutableStateOf(true)

    init {
        carregarFilmesPopulares()
        loadFilmes()
    }

    private fun loadFilmes() {
        searchFilmes("Avengers")
    }

    private fun carregarFilmesPopulares() {

        viewModelScope.launch {


            try {
                when(val response = repository.getPopularMovies()) {
                    is Resource.Success -> {
                        list = response.data!!
                        if (list.isNotEmpty()) isLoading = false
                    }
                    is Resource.Error -> {
                        isLoading = false
                        Log.e("Network", "popularMovies: Failed getting filmes", )
                    }
                    else -> {isLoading = false}
                }

            }catch (exception: Exception){
                isLoading = false
                Log.d("Network", "popularMovies: ${exception.message.toString()}")
            }

        }


    }

    private fun searchFilmes(query: String) {

        viewModelScope.launch {

            if (query.isEmpty()){
                return@launch
            }

            try {
                when(val response = repository.searchMovies(query)) {
                    is Resource.Success -> {
                        list2 = response.data!!
                        if (list2.isNotEmpty()) isLoading2 = false
                    }
                    is Resource.Error -> {
                        isLoading2= false
                        Log.e("Network", "searchFilmes: Failed getting filmes", )
                    }
                    else -> {isLoading2 = false}
                }

            }catch (exception: Exception){
                isLoading2 = false
                Log.d("Network", "searchFilmes: ${exception.message.toString()}")
            }

        }


    }


}
