package com.example.recyclerview_advanced.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.rickandmorty.di.CharacterConverters
import com.example.rickandmorty.util.Constants.CHARACTER_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = CHARACTER_TABLE)
@TypeConverters(CharacterConverters::class)
data class Character(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val type: String? = null,
    val gender: String? = null,
    @Embedded(prefix = "origin_")
    val origin: Origin? = null,
    @Embedded(prefix = "location_")
    val location: Location? = null,
    val image: String? = null,
    val episode: List<String> = emptyList(),
    val url: String? = null,
    val created: String? = null
)