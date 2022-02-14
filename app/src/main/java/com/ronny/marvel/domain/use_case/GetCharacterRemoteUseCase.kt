package com.ronny.marvel.domain.use_case

import com.ronny.marvel.common.interactor.FlowUseCase
import com.ronny.marvel.common.util.Resource
import com.ronny.marvel.data.exception.Failure
import com.ronny.marvel.domain.repository.CharacterRepository
import com.ronny.marvel.presentation.model.CharacterView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharacterRemoteUseCase  @Inject constructor(private val repository: CharacterRepository) :
    FlowUseCase<GetCharacterRemoteUseCase.Params,Unit>(Dispatchers.IO) {
    override fun execute(parameters: GetCharacterRemoteUseCase.Params): Flow<Resource<Failure, Unit>>
        = flow {
        repository.getCharacterRemote(parameters.offset)
       }


    data class Params(val offset: Int = 0)
}