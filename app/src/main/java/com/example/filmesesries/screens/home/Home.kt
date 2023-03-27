package com.example.filmesesries.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.filmesesries.components.MovieAppBar
import com.example.filmesesries.navigation.MovieScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Home(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    
    Scaffold(topBar = {
        
        MovieAppBar(
            title = "Filmes",
            icon = Icons.Default.ArrowBack,
            color = Color.Transparent,
            navController = navController)
        
    }) {
        Surface( modifier = Modifier.fillMaxSize() ) {
            Column() {
                Text(text = "Popular Movies",
                    fontSize = 23.sp,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(start = 15.dp))

                MovieList(navController = navController)


            }
        }
    }



    

    }

@Composable
fun MovieList(navController: NavController,
              viewModel: HomeViewModel = hiltViewModel()) {

    val listOfFilmes = viewModel.list
    if (viewModel.isLoading){
        LinearProgressIndicator()
    }else{

        LazyRow(modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues( 16.dp ) ) {
            items(items = listOfFilmes) {filme ->
                FilmeRow(filme, navController as NavHostController)
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
                navController.navigate(MovieScreens.DetailsScreen.name + "/${filme.id}")
            }
            // .fillMaxWidth()
            .height(230.dp)
            .width(150.dp)
            //.fillMaxHeight()
            .padding(2.dp),
            shape = RectangleShape,
            elevation = 7.dp) {



            val imageUrl: Any = if(filme.poster_path.isEmpty())
                "https://images.unsplash.com/photo-1541963463532-d68292c34b19?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=80&q=80"
            else {
                    "https://image.tmdb.org/t/p/w500" + filme.poster_path
            }




            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally) {


                Image(painter = rememberImagePainter(data = imageUrl.toString() ),
                    contentDescription = "filme image",
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp),
                )
                //Text(text = filme.title)
                //Text(text = filme.release_date)
                //Text(text = filme.overview)
                //Text(text = filme.popularity.toString())
                //Text(text = filme.vote_average.toString())
                //Text(text = filme.id.toString())
                Text(text = "⭐" + filme.vote_average.toString(),
                modifier = Modifier.padding(start = 1.dp),
                fontSize = 15.sp,
                )
            }



    }

}

