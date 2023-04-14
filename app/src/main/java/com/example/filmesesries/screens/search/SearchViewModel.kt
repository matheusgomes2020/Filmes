package com.example.filmesesries.screens.search

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
class SearchViewModel @Inject constructor( private val repository: FilmesRepository)
    : ViewModel() {

    var list: List<com.example.filmesesries.model.ResultX> by mutableStateOf(listOf())
    var isLoading: Boolean by mutableStateOf(true)

    init {
        loadMovies()
    }
    private fun  loadMovies() {
        searchMovies("Avatar")
    }
    fun searchMovies(query: String) {

        viewModelScope.launch {

            if (query.isEmpty()) {
                return@launch
            }

            try {
                when(val response = repository.searchMovies(query)) {
                    is Resource.Success -> {
                        list = response.data!!
                        if (list.isNotEmpty()) isLoading = false
                    }
                    is Resource.Error -> {
                        isLoading = false
                        Log.e("Network", "searchFilmes: Failed getting filmes", )
                    }
                    else -> { isLoading = false }
                }

            }catch (exception: Exception){
                isLoading = false
                Log.d("Network", "searchFilmes: ${exception.message.toString()}")
            }

        }


    }


}