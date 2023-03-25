package com.example.filmesesries.repository

import com.google.firebase.firestore.Query
import javax.inject.Inject

class FireRepository @Inject constructor(
    private val queryFilmes: Query
) {

    suspend fun getAllBooksFromDatabase(): String {
        return ""
    }


}