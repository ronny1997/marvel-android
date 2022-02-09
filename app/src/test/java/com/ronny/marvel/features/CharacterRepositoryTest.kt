package com.ronny.marvel.features

import com.ronny.marvel.common.util.Resource
import com.ronny.marvel.data.exception.Failure
import com.ronny.marvel.data.CharacterRepository
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