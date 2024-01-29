package com.example.rickandmorty.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.recyclerview_advanced.entities.Character
import com.example.rickandmorty.R
import com.example.rickandmorty.data.paging.CharactersPagingSource

private val pageData by lazy { CharactersPagingSource.pager() }

@Composable
fun ListCharacters() {
    // Данное преобразование позволит нам преобразовать в ленивую загрузку элементов и
    // Composable представлений состояний
    // Таким образом, полученное состояние позволит сигнализировать о том,
    // что список обнавился и данные в JetPack Compose необходимо отобразить
    val items: LazyPagingItems<Character> = pageData.flow.collectAsLazyPagingItems()

    LazyColumn {
        items(items) {
            it
        }
    }
}
@Composable
fun Characters() {
    Card(
        colors = CardDefaults.cardColors(
            contentColor = Color.Gray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_launcher_background
                ),
                contentDescription = "image",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.padding(start = 10.dp))
            Information()
        }
    }
}

@Composable
private fun Information() {
    Column {
        Text(text = "name")
        Row(modifier = Modifier.padding(top = 10.dp)) {
            Image(
                painter = painterResource(id = R.drawable.circle),
                contentDescription = "circle",
                modifier = Modifier.size(width = 10.dp, height = 19.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "gender")
        }
        Text(
            text = "location",
            modifier = Modifier.padding(top = 10.dp)
        )
        Text(
            text = "lastLocation",
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCharacters() {
    ListCharacters()
}