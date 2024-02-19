package com.example.rickandmorty.screens.Details

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.recyclerview_advanced.entities.Character
import com.example.rickandmorty.ui.theme.DarkGreen

@Composable
fun PersonDetails(
    characterId: Int,
    personDetailsViewModel: PersonDetailsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        // Запускаем запрос на получение данных о персонаже
        personDetailsViewModel.getCharacter(characterId)
    }

    // Подписываемся на изменения данных о пезрсонаже и ViewModel
    val characterState by personDetailsViewModel.character.collectAsState()
    Log.d("CHARACTER", characterState.toString())
    characterState?.let {
        Content(character = it)
    }
}

@Composable
fun Content(character: Character) {
    Surface (
        color = DarkGreen
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = character.image),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = character.name ?: "")

            Text(
                text = "Live status:",
                color = Color.White
            )

            Text(text = "Species and gender:")

            Text(text = "Last known location:")

            Text(text = "First screen in:")

            Text(text = "Episodes:")
        }
    }
}


@Preview (showSystemUi = true)
@Composable
fun Preview() {
    Content(Character())
}