package com.example.filmesesries.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.filmesesries.screens.details.DetailsScreen
import com.example.filmesesries.screens.home.Home
import com.example.filmesesries.screens.home.HomeViewModel

@Composable
fun FilmesNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = MovieScreens.HomeScreen.name ) {


        composable(MovieScreens.HomeScreen.name) {
            val searchViewModel = hiltViewModel<HomeViewModel>()

            Home(navController = navController, viewModel = searchViewModel)
        }

        val detailName = MovieScreens.DetailsScreen.name
        composable("$detailName/{movieId}", arguments = listOf(navArgument("movieId"){
            type = NavType.StringType
        })) { backStackEntry ->
            backStackEntry.arguments?.getString("movieId").let {

                DetailsScreen(navController = navController, movieId = it.toString())
            }
        }

    }
}