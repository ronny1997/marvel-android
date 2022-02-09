package com.ronny.marvel.domain.repository

import com.ronny.marvel.common.util.Resource
import com.ronny.marvel.data.exception.Failure
import com.ronny.marvel.domain.model.CharacterItem
import com.ronny.marvel.presentation.model.CharacterItemView
import com.ronny.marvel.presentation.model.CharactersListView
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend  fun getCharacter(
        offset: Int
    ): CharactersListView?

    suspend fun getCharacterByID(
        id: Int
    ): CharacterItem?
}