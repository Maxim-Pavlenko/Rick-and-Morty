package com.example.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.emtities.CharacterKeys

@Dao
interface CharacterKeysDao {
    @Query("SELECT * FROM character_keys_table WHERE id =:id")
    suspend fun getRemoteKeys(id: String): CharacterKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<CharacterKeys>)

    @Query("DELETE FROM character_keys_table")
    suspend fun deleteAllRemoteKeys()
}