package com.ronny.marvel.domain.repository

import com.ronny.marvel.data.local.marvel.entity.CharacterEntity
import com.ronny.marvel.domain.model.Character
import com.ronny.marvel.presentation.model.CharacterView
import com.ronny.marvel.presentation.model.MarvelDataView
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
      fun getCharacter(
    ): Flow<List<CharacterEntity>>

      suspend fun getCharacterRemote( offset: Int)
    suspend fun getCharacterByID(
        id: Int
    ): Character?
}