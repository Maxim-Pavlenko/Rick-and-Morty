package com.example.rickandmorty.data.remote

import com.example.recyclerview_advanced.entities.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersAPI {
    @GET("character")
    suspend fun getCharacters(
        @Query("count") count: Int,
        @Query("page") page:Int
    ): CharacterResponse
}