package com.example.rickandmorty.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.recyclerview_advanced.entities.Character
import com.example.rickandmorty.data.local.CharacterDataBase
import com.example.rickandmorty.data.paging.CharacterRemoteMediator
import com.example.rickandmorty.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val characterDataBase: CharacterDataBase
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getAllCharacters(): Flow<PagingData<Character>> {
        val pagingSourceFactory = {characterDataBase.characterDao().getAllCharacters()}
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = CharacterRemoteMediator(characterDataBase = characterDataBase),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}