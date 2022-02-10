package com.ronny.marvel.domain.use_case

import com.ronny.marvel.common.interactor.FlowUseCase
import com.ronny.marvel.common.util.Resource
import com.ronny.marvel.data.exception.Failure
import com.ronny.marvel.domain.model.toCharacterView
import com.ronny.marvel.domain.repository.CharacterRepository
import com.ronny.marvel.presentation.model.CharacterView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(private val repository: CharacterRepository) :
    FlowUseCase<GetCharacterByIdUseCase.Params, CharacterView?>(Dispatchers.IO) {
    override fun execute(parameters: Params): Flow<Resource<Failure, CharacterView?>> {
        return flow {
            runCatching {
                repository.getCharacterByID(parameters.id)
            }.onSuccess { data ->
                emit(Resource.Success(data?.toCharacterView()))
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
    }

    data class Params(val id: Int = 0)
}