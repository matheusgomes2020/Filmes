package com.example.filmesesries.di

import com.example.filmesesries.network.FilmesApi
import com.example.filmesesries.repository.FilmesRepository
import com.example.filmesesries.repository.FireRepository
import com.example.filmesesries.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn( SingletonComponent::class )
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseRepository()
    = FireRepository( queryFilmes = FirebaseFirestore.getInstance()
        .collection("filmes"))

    @Singleton
    @Provides
    fun provideFilmesRepository( api: FilmesApi ) = FilmesRepository( api )

    @Singleton
    @Provides
    fun provideFilmesApi() : FilmesApi {

        return Retrofit.Builder()
            .baseUrl( Constants.BASE_URL )
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
            .create( FilmesApi::class.java )

    }

}