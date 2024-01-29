package com.example.rickandmorty.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.recyclerview_advanced.entities.Character
import com.example.rickandmorty.data.local.CharacterDataBase
import com.example.rickandmorty.data.remote.CharactersAPI
import com.example.rickandmorty.emtities.CharacterKeys
import javax.inject.Inject

@Suppress("IMPLICIT_CAST_TO_ANY")
@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator @Inject constructor(
    private val characterDataBase: CharacterDataBase
): RemoteMediator<Int, Character>() {

    private val characterDao = characterDataBase.characterDao()
    private val caharacterKeysDao = characterDataBase.characterKeys()
    private fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Character>): CharacterKeys? {
        TODO("Not yet implemented")
    }

    private fun getRemoteKeyForFirstItem(state: PagingState<Int, Character>): CharacterKeys? {

    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {
        return try {
            val currentPage = when(loadType) {
                // Содержимое данной подкачки обнавляется
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(

                        )
                }

                LoadType.APPEND -> TODO()
            }


            return MediatorResult()
        }
    }
}