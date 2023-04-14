package com.example.filmesesries.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.filmesesries.components.InputField
import com.example.filmesesries.components.MovieAppBar
import com.example.filmesesries.navigation.MovieScreens
import com.example.filmesesries.screens.home.HomeViewModel
import com.example.filmesesries.screens.home.MovieList

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {

    Scaffold(topBar = {
        MovieAppBar(
            title = "Filmes",
            icon = Icons.Default.ArrowBack,
            color = Color.Transparent,
            navController = navController,
            onArrowBackClicked = {
                navController.popBackStack()
            },)

    }) {
        Surface( modifier = Modifier.fillMaxSize() ) {
            Column {
                
                Text(text = "Search movie",
                    fontSize = 23.sp,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(start = 15.dp))
                
                SearchForm(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)) {searchQuery ->
                            viewModel.searchMovies( searchQuery )
                        }
                Spacer(modifier = Modifier.height( 13.dp ))

                MovieList2(navController = navController)


            }
        }
    }

}

@Composable
fun MovieList2(navController: NavController,
              viewModel: SearchViewModel = hiltViewModel()) {

    val listOfFilmes = viewModel.list
    if (viewModel.isLoading){
        LinearProgressIndicator()
    }else{

        LazyColumn(modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues( 16.dp ) ) {
            items(items = listOfFilmes) {filme ->
                FilmeRow2(filme, navController as NavHostController)
            }
        }


    }

}


@Composable
fun FilmeRow2(
    filme: com.example.filmesesries.model.ResultX,
    navController: NavHostController
) {

    Card(
        modifier = Modifier
            .clickable {
                navController.navigate(MovieScreens.DetailsScreen.name + "/${filme.id}")
            }
            .fillMaxWidth()
            .height(100.dp)
            //.width(150.dp)
            //.fillMaxHeight()
            .padding(2.dp),
        shape = RectangleShape,
        elevation = 7.dp) {



        val imageUrl: Any = if(filme.poster_path.isEmpty())
            "https://images.unsplash.com/photo-1541963463532-d68292c34b19?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=80&q=80"
        else {
            "https://image.tmdb.org/t/p/w500" + filme.poster_path
        }

            Row() {

                Image(painter = rememberImagePainter(data = imageUrl.toString() ),
                    contentDescription = "filme image",
                    modifier = Modifier
                        .width(80.dp)
                        .fillMaxHeight()
                )

                Column(modifier = Modifier.padding(top = 10.dp)) {

                Text(text = filme.title,
                fontFamily = FontFamily.SansSerif)
                Text(text = filme.release_date)
                //Text(text = filme.overview)
                //Text(text = filme.popularity.toString())
                Text(text = filme.vote_average.toString())
                //Text(text = filme.id.toString())
            }


        }



    }

}

@ExperimentalComposeUiApi
@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    hint: String = "Search",
    onSearch: (String) -> Unit = {}) {
    Column {
        val searchQueryState = rememberSaveable { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(searchQueryState.value) {
            searchQueryState.value.trim().isNotEmpty()

        }

        InputField(valueState = searchQueryState,
            labelId = "Search",
            enabled = true,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            })

    }


}



