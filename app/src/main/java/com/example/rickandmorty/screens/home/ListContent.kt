package com.example.rickandmorty.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.rememberAsyncImagePainter
import com.example.recyclerview_advanced.entities.Character
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.theme.DarkGreen
import com.example.rickandmorty.ui.theme.DarkGreen2
import com.example.rickandmorty.ui.theme.Moonstone

@Composable
fun ListContent(
    lazyPagingItems: LazyPagingItems<Character>,
    onClick: (Character) -> Unit
) {
    Surface (
        color = DarkGreen
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(all = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                count = lazyPagingItems.itemCount,
                key = lazyPagingItems.itemKey{ it.id!! }
            ) { index ->
                lazyPagingItems[index]?.let {
                    Characters(it, onClick)
                }
            }
        }
    }
}


@Composable
fun Characters(
    character: Character,
    onClick: (Character) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = DarkGreen2,
            contentColor = Moonstone
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable(onClick = { onClick(character) })
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(model = character.image),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.padding(start = 10.dp))
            Information(character)
        }
    }
}


@Composable
private fun Information(character: Character) {
    Column {
        Text (
            text = character.name ?: "",
            modifier = Modifier.padding(top = 10.dp)
        )
        Row(modifier = Modifier.padding(top = 10.dp)) {
            Image(
                painter = painterResource(id = R.drawable.circle),
                contentDescription = "circle",
                modifier = Modifier.size(width = 10.dp, height = 19.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = character.gender ?: "")
        }
        Text(
            text = character.location?.name ?: "",
            modifier = Modifier.padding(top = 10.dp)
        )
        Text(
            text =character.species ?: "",
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}
