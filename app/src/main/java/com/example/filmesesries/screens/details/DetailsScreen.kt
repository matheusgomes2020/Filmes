package com.example.filmesesries.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.filmesesries.components.MovieAppBar
import com.example.filmesesries.data.Resource
import com.example.filmesesries.navigation.MovieScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen( //filmeInfo: Resource<com.example.filmesesries.model.Result>,
                   navController: NavController,
                   movieId: String,
                   viewModel: DetailsViewModel = hiltViewModel() ) {

    val movieInfo = produceState<Resource<com.example.filmesesries.model.Result>>(initialValue = Resource.Loading()){
        value = viewModel.getMovieInfo(movieId)
    }.value

    val movieData = movieInfo.data

    Scaffold(topBar = {

        (if (movieData != null ) movieInfo.data?.title else "Movie")?.let {
            MovieAppBar(title = it,
                color = Color.Transparent ,
                icon = Icons.Default.ArrowBack,
                icon2 = Icons.Default.Settings,
                navController = navController,
                onArrowBackClicked = {
                    navController.popBackStack()
                },
            onIconClicked = {

            })
        }

    }) {

        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxSize()) {
            Column(modifier = Modifier.padding(top = 12.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {



                if (movieInfo.data == null) {
                    Row() {
                        LinearProgressIndicator()
                        Text(text = "Loading...")
                    }

                }else{
                    FilmeRowDetalhes(movieInfo, navController as NavHostController)
                }
                //  Log.d("Deets", "BookDetailsScreen: ${bookInfo.data.toString()}")


            }


        }

    }


    
}


@Composable
fun FilmeRowDetalhes(
    filmeInfo: Resource<com.example.filmesesries.model.Result>,
    navController: NavHostController
) {

    val movieData = filmeInfo.data




                val imageUrl: Any = if(movieData!!.poster_path.isEmpty())
                    "https://images.unsplash.com/photo-1541963463532-d68292c34b19?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=80&q=80"
                else {
                    "https://image.tmdb.org/t/p/w500" + movieData.poster_path
                }

    Column(
        Modifier
            .padding(4.dp)
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

        Image(painter = rememberImagePainter(data = imageUrl.toString() ),
            contentDescription = "filme image",
            modifier = Modifier
                .width(650.dp)
                //.fillMaxWidth()
                .height(500.dp)
            //.fillMaxHeight()
            //.padding(end = 5.dp),
        )

        Column(
            Modifier
                .width(600.dp)
                .padding(start = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

                Text(text = movieData.title,
                fontWeight = FontWeight.Bold
                )
                //Text(text = movieData.release_date)
                Text(text = movieData.overview,
                    modifier = Modifier.padding(top = 10.dp))
                //Text(text = movieData.popularity.toString())
                //Text(text = movieData.vote_average.toString())
                //Text(text = movieData.id.toString())
        }



    }















}