package com.example.movieexplorer.presentation.nav_host

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieexplorer.presentation.home_movies.components.HomeContent
import com.example.movieexplorer.presentation.movie_details.components.MovieDetail
import com.example.movieexplorer.util.Screen

@Composable
fun MovieNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeMovies.route,
        modifier = modifier
    ) {

        composable(
            route = Screen.HomeMovies.route,
        ) {
            HomeContent(modifier = Modifier.fillMaxSize(), onNavigateToDetails = { _ ->
            })
        }

        composable(
            route = Screen.MovieDetails.routeWithArgs,
            arguments = Screen.MovieDetails.navArgument
        ) {
            MovieDetail(modifier = Modifier.fillMaxSize())
        }
    }
}

private fun navigateToDetailsScreen(navController: NavHostController, movieId: String) {
    navController.navigate(Screen.MovieDetails.createRoute(movieId = movieId)) {
        launchSingleTop = true
        restoreState = true
    }
}
