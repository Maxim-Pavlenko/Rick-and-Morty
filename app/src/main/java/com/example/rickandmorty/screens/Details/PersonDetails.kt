package com.example.rickandmorty.screens.Details

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.recyclerview_advanced.entities.Character
import com.example.rickandmorty.emtities.Episode
import com.example.rickandmorty.ui.theme.DarkGreen
import com.example.rickandmorty.ui.theme.DarkGreen2
import com.example.rickandmorty.ui.theme.White

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
    val episodeState by personDetailsViewModel.episods.collectAsState()

    characterState?.let {
        Content(character = characterState!!, episodeState)
    }
}

@Composable
fun Content(character: Character, episodeState:  List<Episode>) {
    LazyColumn(
        modifier = Modifier
            .background(DarkGreen)
            .fillMaxSize()
    ) {

        item {
            Image(
                painter = rememberAsyncImagePainter(model = character.image),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }

        item {
            Column(
                modifier = Modifier
                    .padding(start = 25.dp)
                    .fillMaxSize()
            ) {
                InfornationCharacter(character, episodeState)
            }
        }

        items(episodeState) { episode ->
            Episode(episode)
        }
    }
}

@Composable
private fun InfornationCharacter(
    character: Character,
    firstEpisode: List<Episode>,
) {
    val (headStyle, subHeadStyle, textStyle) = creteStylesForInformation()

    Text(
        text = character.name,
        style = headStyle
    )

    Text(
        text = "Live status:",
        style = subHeadStyle,
        modifier = Modifier.padding(top = 15.dp)
    )

    Text(
        text = character.status,
        style = textStyle
    )

    Text(
        text = "Species and gender:",
        style = subHeadStyle,
        modifier = Modifier.padding(top = 15.dp)
    )

    Text(
        text = character.species + "(" + character.gender + ")",
        style = textStyle
    )

    Text(
        text = "Last known location:",
        style = subHeadStyle,
        modifier = Modifier.padding(top = 15.dp)
    )

    Text(
        text = character.location?.name ?: "",
        style = textStyle
    )

    Text(
        text = "First screen in:",
        style = subHeadStyle,
        modifier = Modifier.padding(top = 15.dp)
    )

    Text(
        text = if (firstEpisode.isEmpty()) "" else firstEpisode.first().name,
        style = textStyle
    )

    Text(
        text = "Episodes:",
        style = headStyle,
        modifier = Modifier.padding(top = 20.dp)
    )
}


@Composable
private fun Episode(episode: Episode) {
    val (nameStyle, airDateStyle, episodeStyle) = creteStylesForEpisodes()
    Card(
        shape = RoundedCornerShape(
            topEnd = 16.dp,
            bottomEnd = 16.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = DarkGreen2,
            contentColor = White),
        modifier = Modifier
            .padding(top = 10.dp, end = 10.dp, bottom = 10.dp)
            .fillMaxWidth()

    )
    {
        Row(
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp, top = 10.dp, bottom = 10.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding()
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            ) {
                Text(
                    text = episode.name,
                    style = nameStyle
                )
                Text(
                    text = episode.airDate ?: "",
                    style = airDateStyle
                )
            }
            Text(
                text = episode.episode,
                style = episodeStyle,
                modifier = Modifier.padding()
            )
        }
    }
}

@Composable
private fun creteStylesForInformation(): Triple<TextStyle, TextStyle, TextStyle> {
    val headStyle = TextStyle(
        color = Color.White,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold
    )

    val subHeadStyle = TextStyle(
        color = Color.Gray,
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal
    )

    val textStyle = TextStyle(
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal
    )
    return Triple(headStyle, subHeadStyle, textStyle)
}

@Composable
private fun creteStylesForEpisodes(): Triple<TextStyle, TextStyle, TextStyle> {
    val nameStyle = TextStyle(
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )

    val airDateStyle = TextStyle(
        color = Color.White,
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal
    )

    val episodeStyle = TextStyle(
        color = Color.Gray,
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal
    )
    return Triple(nameStyle, airDateStyle, episodeStyle)
}

@Preview (showSystemUi = true)
@Composable
fun Preview() {
    Episode(Episode(0, "gssdgsdg","March 17, 2014","gssdgsdg" ))
}