package com.ronny.marvel.features.characters.characterslist.usecase

import com.ronny.marvel.core.common.Resource
import com.ronny.marvel.core.exception.Failure
import com.ronny.marvel.core.interactor.FlowUseCase
import com.ronny.marvel.features.characters.CharacterRepository
import com.ronny.marvel.features.characters.model.CharacterItemView
import com.ronny.marvel.features.characters.model.CharactersListDto
import com.ronny.marvel.features.characters.model.CharactersListView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(private val repository: CharacterRepository) :
    FlowUseCase<GetCharacterByIdUseCase.Params, CharacterItemView?>(Dispatchers.IO) {
    override fun execute(parameters: Params): Flow<Resource<Failure, CharacterItemView?>> =
        repository.getCharacterByID(parameters.id)

    data class Params(val id: Int = 0)
}