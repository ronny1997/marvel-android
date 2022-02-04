package com.ronny.marvel.features.characters

import com.ronny.marvel.BuildConfig.PRIVATE_API_KEY
import com.ronny.marvel.core.common.Constants.LIMIT_CHARACTERS
import com.ronny.marvel.core.common.Constants.PUBLIC_KEY
import com.ronny.marvel.core.common.Resource
import com.ronny.marvel.core.exception.Failure
import com.ronny.marvel.core.extensions.encryptMD5
import com.ronny.marvel.core.platform.NetworkHandler
import com.ronny.marvel.core.platform.request
import com.ronny.marvel.features.characters.model.CharactersListDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

interface CharacterRepository {
    fun getCharacter(

    ): Flow<Resource<Failure, CharactersListDto>>

    class CharacterRepositoryImpl @Inject constructor(
        private val remoteServiceRemote: CharacterRemoteService,
        private val networkHandler: NetworkHandler
    ) : CharacterRepository {
        override fun getCharacter(): Flow<Resource<Failure, CharactersListDto>> = flow {
            when (networkHandler.isNetworkAvailable()) {
                true -> {
                    val ts = Date().time.toString()
                    val hash = (ts + PRIVATE_API_KEY + PUBLIC_KEY).encryptMD5()
                    emit(
                        request(
                            remoteServiceRemote.getCharactersList(
                                LIMIT_CHARACTERS,
                                ts,
                                PUBLIC_KEY,
                                hash
                            ),
                            {
                                it
                            },
                            CharactersListDto()
                        )
                    )
                }
                false -> emit(Resource.Error(Failure.NetworkConnection(errorMessage = "No Network")))
            }
        }

    }
}