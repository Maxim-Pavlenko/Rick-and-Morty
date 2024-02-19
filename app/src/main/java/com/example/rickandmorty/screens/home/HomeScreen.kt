package com.example.rickandmorty.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.recyclerview_advanced.entities.Character
import com.example.rickandmorty.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val onClick: (Character) -> Unit = {
        navController.navigate("details_screen/${it.id}")
    }

    LaunchedEffect(key1 = Unit) {
        homeViewModel.getAllCharacters()
    }
    val getAllCharacters = homeViewModel.characterFlow.collectAsLazyPagingItems()
    ListContent(lazyPagingItems = getAllCharacters, onClick)
}