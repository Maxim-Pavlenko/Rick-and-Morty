package com.example.rickandmorty.emtities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.util.Constants.CHARACTER_KEYS_TABLE

@Entity(tableName = CHARACTER_KEYS_TABLE)
data class CharacterKeys (
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)