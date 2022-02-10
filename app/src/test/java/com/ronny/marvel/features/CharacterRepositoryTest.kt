package com.ronny.marvel.features

import com.ronny.marvel.common.util.Resource
import com.ronny.marvel.data.exception.Failure
import com.ronny.marvel.data.remote.dto.MarvelDataDto
import com.ronny.marvel.domain.model.CharacterItem
import com.ronny.marvel.domain.repository.CharacterRepository
import com.ronny.marvel.presentation.model.MarvelDataView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepositoryTest : CharacterRepository {
    var charactersListDto = MarvelDataDto()
    override suspend fun getCharacter(offset: Int): MarvelDataView? {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterByID(id: Int): CharacterItem? {
        TODO("Not yet implemented")
    }

}