package com.example.movieexplorer.util

import androidx.navigation.NavType
import androidx.navigation.navArgument

private const val HOME_MOVIES = "homeMovies"
private const val MOVIE_DETAIL = "movieDetail"
const val MOVIE_ID_ARG = "movieId"

sealed class Screen(val route: String) {
    data object HomeMovies : Screen(HOME_MOVIES)

    data object MovieDetails : Screen(MOVIE_DETAIL) {

        val routeWithArgs = "$route?$MOVIE_ID_ARG={$MOVIE_ID_ARG}"
        fun createRoute(movieId: Int) = "$route?$MOVIE_ID_ARG=$movieId"

        val navArgument = listOf(navArgument(name = MOVIE_ID_ARG) {
            type = NavType.IntType
            defaultValue = -1
        })
    }
}