package com.ronny.marvel.features.characters.characterslist.usecase

import com.ronny.marvel.core.common.Resource
import com.ronny.marvel.core.exception.Failure
import com.ronny.marvel.core.interactor.FlowUseCase
import com.ronny.marvel.features.characters.CharacterRepository
import com.ronny.marvel.features.characters.model.CharactersListDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacters @Inject constructor(private val repository: CharacterRepository) :
    FlowUseCase<GetCharacters.Params, CharactersListDto>(Dispatchers.IO) {
    override fun execute(parameters: Params): Flow<Resource<Failure, CharactersListDto>> = repository.getCharacter(parameters.offset)

    data class Params(val offset: Int = 0)
}