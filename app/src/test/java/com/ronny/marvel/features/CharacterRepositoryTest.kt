package com.ronny.marvel.features

import com.ronny.marvel.core.common.Resource
import com.ronny.marvel.core.exception.Failure
import com.ronny.marvel.features.characters.CharacterRepository
import com.ronny.marvel.features.characters.model.CharactersListDto
import com.ronny.marvel.features.characters.model.CharactersListView
import com.ronny.marvel.features.characters.model.toCharactersListView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepositoryTest : CharacterRepository {
    var charactersListDto = CharactersListDto()

    override fun getCharacter(offset: Int): Flow<Resource<Failure, CharactersListView>> = flow {
        emit(Resource.Success(charactersListDto.toCharactersListView()))
    }

}