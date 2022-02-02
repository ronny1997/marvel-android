package com.ronny.marvel.core.interactor

import com.ronny.marvel.core.common.Resource
import com.ronny.marvel.core.exception.Failure
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    operator fun invoke(parameters: P): Flow<Resource<Failure, R>> {
        return execute(parameters)
            .flowOn(coroutineDispatcher)
    }

    abstract fun execute(parameters: P): Flow<Resource<Failure, R>>
}

class None