package com.ronny.marvel.data.remote

import com.ronny.marvel.data.remote.dto.MarvelDataDto
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject

class MarvelRemoteDataSourceImpl @Inject constructor(retrofit: Retrofit) : MarvelRemoteDataSource {

    private val marvelApi by lazy { retrofit.create(CharacterApi::class.java) }

    override  fun getCharactersList(
        offset: String,
        limit: String,
        ts: String,
        apikey: String,
        hash: String
    ): Call<MarvelDataDto> =
        marvelApi.getCharactersList(offset, limit, ts, apikey, hash)

    override suspend fun getCharacterById(
        id: Int,
        ts: String,
        apikey: String,
        hash: String
    ): MarvelDataDto? = marvelApi.getCharacterById(id, ts, apikey, hash)

}