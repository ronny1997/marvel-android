package com.ronny.marvel.domain.repository

import com.ronny.marvel.common.util.Resource
import com.ronny.marvel.common.util.Failure
import com.ronny.marvel.data.local.marvel.entity.CharacterEntity
import com.ronny.marvel.domain.model.Character
import com.ronny.marvel.domain.model.MarvelData
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacter(
    ): Flow<List<CharacterEntity>>

     fun getCharacterRemote(
        offset: Int,
        ts: String,
        hash: String
    ): Resource<Failure, MarvelData>

    fun getCharacterList(): List<CharacterEntity>?

    fun saveCharacterList(characterDtoList: List<Character>?)

    fun getCharacterByID(
        id: Int
    ): Character?
}