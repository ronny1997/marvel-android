package com.ronny.marvel.domain.use_case

import com.ronny.marvel.common.interactor.FlowUseCase
import com.ronny.marvel.common.interactor.None
import com.ronny.marvel.common.util.Resource
import com.ronny.marvel.common.util.Failure
import com.ronny.marvel.data.local.marvel.entity.toCharacterView
import com.ronny.marvel.domain.repository.CharacterRepository
import com.ronny.marvel.presentation.model.CharacterView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: CharacterRepository) :
    FlowUseCase<None, List<CharacterView>>(Dispatchers.IO) {
    override fun execute(none: None): Flow<Resource<Failure, List<CharacterView>>> =
        flow {
            runCatching {
                repository.getCharacter()
            }.onSuccess { flowList->
                flowList.collect {list->
                    emit(Resource.Success(list.map { it.toCharacterView() }))
                }

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