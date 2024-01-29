package com.example.rickandmorty.data.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.recyclerview_advanced.entities.Character
import com.example.rickandmorty.data.remote.RetrofitInstance

class CharactersPagingSource : PagingSource<Int, Character>() {
    // Возвращает ключ по которому будем определять, что данные необходимо перезагрузить
    // По умолчанию данный ключ равен 0
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> =
        kotlin.runCatching {
            RetrofitInstance.charactersAPI.getCharacter(params.key ?: 0)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it.results,
                    prevKey = params.key?.let { it - 1 },
                    nextKey = (params.key ?: 0) + 1
                )
            },
            onFailure = { throwable ->
                LoadResult.Error(throwable)
            }
        )

    companion object {
        fun pager() = Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { CharactersPagingSource() }
        )
    }
}