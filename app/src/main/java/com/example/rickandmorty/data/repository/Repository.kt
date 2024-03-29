package com.example.rickandmorty.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.recyclerview_advanced.entities.Character
import com.example.rickandmorty.data.local.CharacterDataBase
import com.example.rickandmorty.data.paging.CharacterRemoteMediator
import com.example.rickandmorty.data.remote.RetrofitInstance
import com.example.rickandmorty.emtities.Episode
import com.example.rickandmorty.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import javax.inject.Inject

class Repository @Inject constructor(
    private val characterDataBase: CharacterDataBase
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getAllCharacters(): Flow<PagingData<Character>> {
        val pagingSourceFactory = { characterDataBase.characterDao().getAllCharacters()}
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = CharacterRemoteMediator(characterDataBase = characterDataBase),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    suspend fun getCharacter(characterId: Int): Character {
        return characterDataBase.characterDao().getCharacter(characterId)
    }

    suspend fun getEpisod(episodeNumbers: List<Int>): List<Episode> {
        return RetrofitInstance.charactersAPI.getEpisods(episodeNumbers)
    }
}
