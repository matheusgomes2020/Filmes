package com.example.filmesesries.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.filmesesries.data.Resource
import com.example.filmesesries.navigation.MovieScreens

@Composable
fun DetailsScreen( navController: NavController,
                   movieId: String,
                   viewModel: DetailsViewModel = hiltViewModel() ) {

    Surface(modifier = Modifier
        .padding(3.dp)
        .fillMaxSize()) {
        Column(modifier = Modifier.padding(top = 12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {

            val movieInfo = produceState<Resource<com.example.filmesesries.model.Result>>(initialValue = Resource.Loading()){
                value = viewModel.getMovieInfo(movieId)
            }.value

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


    Text(text = movieId)
    
}


@Composable
fun FilmeRowDetalhes(
    filmeInfo: Resource<com.example.filmesesries.model.Result>,
    navController: NavHostController
) {

    val movieData = filmeInfo.data

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .fillMaxWidth()
            .padding(10.dp),
        shape = RectangleShape,
        elevation = 7.dp) {

        Row(modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.Top) {

            val imageUrl: Any = if(movieData!!.poster_path.isEmpty())
                "https://images.unsplash.com/photo-1541963463532-d68292c34b19?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=80&q=80"
            else {
                "https://image.tmdb.org/t/p/w500" + movieData.poster_path
            }

            Image(painter = rememberImagePainter(data = imageUrl.toString() ),
                contentDescription = "filme image",
                modifier = Modifier.width( 80.dp )
                    .fillMaxHeight()
                    .padding( end = 5.dp ),
            )


            Column() {
                Text(text = movieData.title)
                Text(text = movieData.release_date)
                Text(text = movieData.overview)
                Text(text = movieData.popularity.toString())
                Text(text = movieData.vote_average.toString())
                Text(text = movieData.id.toString())
            }

        }

    }

}