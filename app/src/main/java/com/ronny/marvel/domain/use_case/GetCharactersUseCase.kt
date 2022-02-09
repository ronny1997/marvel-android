package com.ronny.marvel.domain.use_case

import com.ronny.marvel.common.interactor.FlowUseCase
import com.ronny.marvel.common.util.Resource
import com.ronny.marvel.data.exception.Failure
import com.ronny.marvel.domain.model.toCharacterItemView
import com.ronny.marvel.domain.repository.CharacterRepository
import com.ronny.marvel.presentation.model.CharactersListView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: CharacterRepository) :
    FlowUseCase<GetCharactersUseCase.Params, CharactersListView>(Dispatchers.IO) {
    override fun execute(parameters: Params): Flow<Resource<Failure, CharactersListView>> =
         flow {
            runCatching {
                repository.getCharacter(parameters.offset)
            }.onSuccess { data ->
                emit(Resource.Success(data ?: CharactersListView()))
            }.onFailure { exception ->
                emit(
                    Resource.Error(
                        Failure.ServerError(
                            exception.hashCode(),
                            exception.message ?: "Error unknown"
                        )
                    )
                )
            }
        }

    data class Params(val offset: Int = 0)
}