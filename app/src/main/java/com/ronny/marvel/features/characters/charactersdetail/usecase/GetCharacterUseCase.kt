package com.ronny.marvel.features.characters.charactersdetail.usecase

import com.ronny.marvel.core.common.Resource
import com.ronny.marvel.core.exception.Failure
import com.ronny.marvel.core.interactor.FlowUseCase
import com.ronny.marvel.features.characters.CharacterRepository
import com.ronny.marvel.features.characters.model.CharacterItem
import com.ronny.marvel.features.characters.model.CharactersListDto

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(private val repository: CharacterRepository) :
    FlowUseCase<GetCharacterUseCase.Params, CharactersListDto>(Dispatchers.IO) {
    override fun execute(parameters: Params): Flow<Resource<Failure, CharactersListDto>> =
        repository.getCharacterDetail(parameters.id)

    data class Params(val id: Int = 0)
}