package com.example.filmesesries.model

data class Results(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)