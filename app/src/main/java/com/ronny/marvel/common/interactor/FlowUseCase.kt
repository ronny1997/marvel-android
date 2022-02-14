package com.ronny.marvel.common.interactor

import com.ronny.marvel.common.util.Resource
import com.ronny.marvel.common.util.Failure
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