package com.ronny.marvel.data.remote

import com.ronny.marvel.data.remote.dto.MarvelDataDto
import retrofit2.Retrofit
import javax.inject.Inject

class MarvelRemoteDataSource @Inject constructor(retrofit: Retrofit) : CharacterApi {

    private val marvelApi by lazy { retrofit.create(CharacterApi::class.java) }

    override suspend fun getCharactersList(
        offset: String,
        limit: String,
        ts: String,
        apikey: String,
        hash: String
    ): MarvelDataDto? =
        marvelApi.getCharactersList(offset, limit, ts, apikey, hash)

    override suspend fun getCharacterById(
        id: Int,
        ts: String,
        apikey: String,
        hash: String
    ): MarvelDataDto? = marvelApi.getCharacterById(id, ts, apikey, hash)

}