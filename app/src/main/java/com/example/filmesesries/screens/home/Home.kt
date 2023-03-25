package com.example.filmesesries.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.filmesesries.navigation.MovieScreens

@Composable
fun Home(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    
    Text(text = "Popular Movies")

    val listOfFilmes = viewModel.list
    if (viewModel.isLoading){
        LinearProgressIndicator()
    }else{

        LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues( 16.dp ) ) {
            items(items = listOfFilmes) {filme ->
                FilmeRow(filme, navController)
            }
        }


    }
    }

@Composable
fun FilmeRow(
    filme: com.example.filmesesries.model.Result,
    navController: NavHostController ) {

    Card(
        modifier = Modifier
            .clickable {
                navController.navigate( MovieScreens.DetailsScreen.name + "/${filme.id}" )
            }
            .fillMaxWidth()
            .height(250.dp)
            .fillMaxWidth()
            .padding(10.dp),
            shape = RectangleShape,
            elevation = 7.dp) {

        Row(modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.Top) {

            val imageUrl: Any = if(filme.poster_path.isEmpty())
                "https://images.unsplash.com/photo-1541963463532-d68292c34b19?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=80&q=80"
            else {
                    "https://image.tmdb.org/t/p/w500" + filme.poster_path
            }

            Image(painter = rememberImagePainter(data = imageUrl.toString() ),
                contentDescription = "filme image",
                modifier = Modifier.width( 80.dp )
                    .fillMaxHeight()
                    .padding( end = 5.dp ),
            )


            Column() {
                Text(text = filme.title)
                Text(text = filme.release_date)
                Text(text = filme.overview)
                Text(text = filme.popularity.toString())
                Text(text = filme.vote_average.toString())
                Text(text = filme.id.toString())
            }

        }

    }

}

