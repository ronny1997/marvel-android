package com.ronny.marvel.domain.repository

import com.ronny.marvel.domain.model.CharacterItem
import com.ronny.marvel.presentation.model.MarvelDataView

interface CharacterRepository {
    suspend  fun getCharacter(
        offset: Int
    ): MarvelDataView?

    suspend fun getCharacterByID(
        id: Int
    ): CharacterItem?
}