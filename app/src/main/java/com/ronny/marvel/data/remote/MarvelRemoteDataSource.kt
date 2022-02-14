package com.ronny.marvel.data.remote

import com.ronny.marvel.data.remote.dto.MarvelDataDto
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject

interface MarvelRemoteDataSource {
     fun getCharactersList(
        offset: String,
        limit: String,
        ts: String,
        apikey: String,
        hash: String
    ): Call<MarvelDataDto>

    suspend fun getCharacterById(
        id: Int,
        ts: String,
        apikey: String,
        hash: String
    ): MarvelDataDto?

}