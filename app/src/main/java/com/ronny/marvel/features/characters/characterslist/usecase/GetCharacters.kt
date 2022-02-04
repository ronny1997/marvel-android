package com.ronny.marvel.features.characters.characterslist.usecase

import com.ronny.marvel.core.common.Resource
import com.ronny.marvel.core.exception.Failure
import com.ronny.marvel.core.interactor.FlowUseCase
import com.ronny.marvel.core.interactor.None
import com.ronny.marvel.features.characters.CharacterRepository
import com.ronny.marvel.features.characters.model.CharactersListDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacters @Inject constructor(private val repository: CharacterRepository) :
    FlowUseCase<None, CharactersListDto>(Dispatchers.IO) {
    override fun execute(parameters: None): Flow<Resource<Failure, CharactersListDto>> = repository.getCharacter()

}