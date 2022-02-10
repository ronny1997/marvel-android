package com.ronny.marvel.data.repository


import com.ronny.marvel.BuildConfig.PRIVATE_API_KEY
import com.ronny.marvel.common.constans.Constants.LIMIT_CHARACTERS
import com.ronny.marvel.common.constans.Constants.PUBLIC_KEY
import com.ronny.marvel.common.extensions.encryptMD5
import com.ronny.marvel.data.remote.MarvelRemoteDataSource
import com.ronny.marvel.data.remote.dto.toCharactersList
import com.ronny.marvel.data.remote.dto.toCharactersListView
import com.ronny.marvel.domain.model.CharacterItem
import com.ronny.marvel.domain.model.toCharacterItem
import com.ronny.marvel.domain.repository.CharacterRepository
import com.ronny.marvel.presentation.model.MarvelDataView
import java.util.*
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteServiceRemote: MarvelRemoteDataSource,
) : CharacterRepository {

    override suspend fun getCharacter(
        offset: Int
    ): MarvelDataView? {
        val ts = Date().time.toString()
        val hash = (ts + PRIVATE_API_KEY + PUBLIC_KEY).encryptMD5()
        return remoteServiceRemote.getCharactersList(
            offset.toString(),
            LIMIT_CHARACTERS,
            ts,
            PUBLIC_KEY,
            hash
        )?.toCharactersListView()
    }

    override suspend fun getCharacterByID(id: Int): CharacterItem? {
        val ts = Date().time.toString()
        val hash = (ts + PRIVATE_API_KEY + PUBLIC_KEY).encryptMD5()
        return remoteServiceRemote.getCharacterById(
            id,
            ts,
            PUBLIC_KEY,
            hash
        )?.toCharactersList()?.data?.characterItem?.get(0)?.toCharacterItem()
    }
}
