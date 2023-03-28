package com.example.filmesesries.model

data class Results2(
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)