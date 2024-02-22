package com.example.rickandmorty.emtities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.JsonNames

data class Episode(
    val id: Int? = null,
    val name: String = "",
    @SerialName("air_date")
    val airDate: String? = null,
    val episode: String = "",
    val characters: Array<String> = emptyArray(),
    val url: String = "",
    val created: String = ""
)