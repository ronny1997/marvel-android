package com.ronny.marvel.features

import com.ronny.marvel.data.local.marvel.entity.CharacterEntity
import com.ronny.marvel.data.remote.dto.MarvelDataDto
import com.ronny.marvel.domain.model.Character
import com.ronny.marvel.domain.repository.CharacterRepository
import com.ronny.marvel.presentation.model.MarvelDataView
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryTest : CharacterRepository {
    var charactersListDto = MarvelDataDto()
    override suspend fun getCharacter(offset: Int): Flow<List<CharacterEntity>> {
        TODO("Not yet implemented")
    }


    override suspend fun getCharacterByID(id: Int): Character? {
        TODO("Not yet implemented")
    }

}