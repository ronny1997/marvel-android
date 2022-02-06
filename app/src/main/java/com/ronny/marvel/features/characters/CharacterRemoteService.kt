package com.ronny.marvel.features.characters

import com.ronny.marvel.features.characters.model.CharacterItem
import com.ronny.marvel.features.characters.model.CharactersListDto
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject

class CharacterRemoteService @Inject constructor(retrofit: Retrofit) : CharacterApi {

    private val marvelApi by lazy { retrofit.create(CharacterApi::class.java) }

    override fun getCharactersList( offset: String,limit: String, ts: String, apikey: String, hash: String ): Call<CharactersListDto> =
        marvelApi.getCharactersList(offset, limit, ts, apikey, hash)
    override fun getCharacterDetail( id: String, ts: String, apikey: String, hash: String ): Call<CharactersListDto> =
        marvelApi.getCharacterDetail( id,ts, apikey, hash)
}