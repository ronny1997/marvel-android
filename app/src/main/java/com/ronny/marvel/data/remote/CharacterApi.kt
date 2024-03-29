package com.ronny.marvel.data.remote

import com.ronny.marvel.data.remote.dto.MarvelDataDto
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
    ): Call<MarvelDataDto>

    @GET("characters/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): MarvelDataDto?
}