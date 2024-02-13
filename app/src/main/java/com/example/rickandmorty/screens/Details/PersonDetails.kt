package com.example.rickandmorty.screens.Details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.recyclerview_advanced.entities.Character
import com.example.rickandmorty.ui.theme.DarkGreen

@Composable
fun PersonDetails(
    characterId: Int,
    personDetailsViewModel: PersonDetailsViewModel = hiltViewModel()) {
    personDetailsViewModel.character
    Content()
}

@Composable
fun Content(character: Character) {
    Surface (
        color = DarkGreen
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(model = character.image),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
        }
    }
}


@Preview (showSystemUi = true)
@Composable
fun Preview() {
    PersonDetails()
}