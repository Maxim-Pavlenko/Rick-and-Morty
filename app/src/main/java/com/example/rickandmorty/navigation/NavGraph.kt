package com.example.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmorty.screens.Details.PersonDetails
import com.example.rickandmorty.screens.home.HomeScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Details.route) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterID")?.toInt()
            characterId?.let {
                PersonDetails(characterId)
            }
        }
    }
}