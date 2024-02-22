package com.example.rickandmorty.data.remote

import com.example.recyclerview_advanced.entities.CharacterResponse
import com.example.rickandmorty.emtities.Episode
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersAPI {
    @GET("character")
    suspend fun getCharacters(
        @Query("count") count: Int,
        @Query("page") page:Int
    ): CharacterResponse

    @GET("episode/{episodeNumbers}")
    suspend fun getEpisods(
        @Path("episodeNumbers") episodeNumbers: List<Int>
    ): List<Episode>
}