package com.example.recyclerview_advanced.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.util.Constants.CHARACTER_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = CHARACTER_TABLE)
data class Character(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    @Embedded
    val origin: Origin,
    @Embedded
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)