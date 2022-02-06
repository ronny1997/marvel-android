package com.ronny.marvel.features.characters

import com.ronny.marvel.core.common.Constants.LIMIT_CHARACTERS
import com.ronny.marvel.features.characters.model.CharacterItem
import com.ronny.marvel.features.characters.model.CharactersListDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("characters")
    fun getCharactersList(
        @Query("offset") offset: String,
        @Query("limit") limit: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Call<CharactersListDto>

    @GET("characters/{idCharacter}")
    fun getCharacterDetail(
        @Path("idCharacter") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Call<CharactersListDto>

}