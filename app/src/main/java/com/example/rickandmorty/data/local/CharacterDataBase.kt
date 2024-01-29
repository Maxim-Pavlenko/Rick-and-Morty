package com.example.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recyclerview_advanced.entities.Character
import com.example.rickandmorty.data.local.dao.CharacterDao
import com.example.rickandmorty.data.local.dao.CharacterKeysDao
import com.example.rickandmorty.emtities.CharacterKeys

@Database(entities = [Character::class, CharacterKeys::class], version = 1)
abstract class CharacterDataBase: RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun characterKeys(): CharacterKeysDao
}