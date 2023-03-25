package com.example.filmesesries.navigation

enum class MovieScreens {

    SplashScreen,
    SearchScreen,
    HomeScreen,
    DetailsScreen;

    companion object {

        fun fromRoute( route: String ): MovieScreens
                = when( route?.substringBefore("/") ) {

            SplashScreen.name -> SplashScreen
            HomeScreen.name -> HomeScreen
            HomeScreen.name -> HomeScreen
            SearchScreen.name -> SearchScreen
            DetailsScreen.name -> DetailsScreen
            null -> HomeScreen
            else -> throw java.lang.IllegalArgumentException( "Route $route is not recognize" )

        }

    }

}