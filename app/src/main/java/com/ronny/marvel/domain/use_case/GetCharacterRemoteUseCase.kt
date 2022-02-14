package com.ronny.marvel.domain.use_case

import com.ronny.marvel.BuildConfig
import com.ronny.marvel.common.constans.Constants
import com.ronny.marvel.common.extensions.encryptMD5
import com.ronny.marvel.common.interactor.FlowUseCase
import com.ronny.marvel.common.util.Resource
import com.ronny.marvel.common.util.Failure
import com.ronny.marvel.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class GetCharacterRemoteUseCase @Inject constructor(private val repository: CharacterRepository) :
    FlowUseCase<GetCharacterRemoteUseCase.Params, Unit>(Dispatchers.IO) {
    override fun execute(parameters: GetCharacterRemoteUseCase.Params): Flow<Resource<Failure, Unit>> =
        flow {
            val ts = Date().time.toString()
            val hash = (ts + BuildConfig.PRIVATE_API_KEY + Constants.PUBLIC_KEY).encryptMD5()
            val response = repository.getCharacterRemote(parameters.offset, ts, hash)
            when (response) {
                is Resource.Success -> {
                    val offset = response.data?.data?.offset ?: 0
                    val sizeListLocal = repository.getCharacterList()?.size ?: 0
                    if (offset >= sizeListLocal) {
                        repository.saveCharacterList(response.data?.data?.characterItem)
                    }
                }
                is Resource.Error -> {
                    response.error?.let { error ->
                        emit(Resource.Error(error))
                    }
                }
            }

        }


    data class Params(val offset: Int = 0)
}