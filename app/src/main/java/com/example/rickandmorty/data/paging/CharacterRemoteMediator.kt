package com.example.rickandmorty.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.recyclerview_advanced.entities.Character
import com.example.recyclerview_advanced.entities.CharacterResponse
import com.example.rickandmorty.data.local.CharacterDataBase
import com.example.rickandmorty.data.remote.RetrofitInstance
import com.example.rickandmorty.emtities.CharacterKeys
import com.example.rickandmorty.util.Constants
import com.example.rickandmorty.util.Constants.ITEMS_PER_PAGE
import javax.inject.Inject



@Suppress("IMPLICIT_CAST_TO_ANY")
@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator @Inject constructor(
    private val characterDataBase: CharacterDataBase
): RemoteMediator<Int, Character>() {

    private val characterDao = characterDataBase.characterDao()
    private val characterKeysDao = characterDataBase.characterKeysDao()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {
        return try {
            // Получаем текущую страницу
            val currentPage = when(loadType) {
                // Обнавление источника подкачки
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                // Загружает данные при старте приложения
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                // Загружает данные, когда данные закагчиваются
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success (
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = RetrofitInstance.charactersAPI.getCharacters(count = ITEMS_PER_PAGE, page = currentPage)
            val endofPaginationReached = response.results.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endofPaginationReached) null else currentPage + 1

            characterDataBase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    characterDao.deleteAllCharacter()
                    characterKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.results.map { character ->
                    CharacterKeys (
                        id = character.id!!,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                characterKeysDao.addAllRemoteKeys(keys)
                characterDao.addCharacter(response.results)
            }
            MediatorResult.Success(endOfPaginationReached = endofPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Character>): CharacterKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                characterKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Character>): CharacterKeys? {
        return state.pages.firstOrNull {it.data.isNotEmpty()}?.data?.firstOrNull()
            ?.let { character ->
                characterKeysDao.getRemoteKeys(id = character.id!!)
            }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Character>): CharacterKeys? {
        return state.pages.lastOrNull{it.data.isNotEmpty()}?.data?.lastOrNull()
            ?.let { character ->
                characterKeysDao.getRemoteKeys(id = character.id!!)
            }
    }
}
