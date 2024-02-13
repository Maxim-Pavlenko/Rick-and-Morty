package com.example.rickandmorty.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recyclerview_advanced.entities.Character

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character_table")
    fun getAllCharacters(): PagingSource<Int, Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacter(character: List<Character>)

    @Query("DELETE FROM character_table")
    suspend fun deleteAllCharacter()

    @Query("SELECT * FROM character_table WHERE id == :characterId")
    suspend fun getCharacter(characterId: Int): Character
}