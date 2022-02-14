package com.ronny.marvel.data.repository


import com.ronny.marvel.BuildConfig.PRIVATE_API_KEY
import com.ronny.marvel.common.constans.Constants.LIMIT_CHARACTERS
import com.ronny.marvel.common.constans.Constants.PUBLIC_KEY
import com.ronny.marvel.common.extensions.encryptMD5
import com.ronny.marvel.data.local.MarvelLocalDataSource
import com.ronny.marvel.data.local.marvel.entity.CharacterEntity
import com.ronny.marvel.data.remote.MarvelRemoteDataSource
import com.ronny.marvel.data.remote.dto.*
import com.ronny.marvel.domain.model.Character
import com.ronny.marvel.domain.model.toCharacter
import com.ronny.marvel.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteService: MarvelRemoteDataSource,
    private val localService: MarvelLocalDataSource,
) : CharacterRepository {

    override  fun getCharacter(
    ): Flow<List<CharacterEntity>> {
        return getCharacterListFlow()
    }

   override suspend fun getCharacterRemote( offset: Int){
        val ts = Date().time.toString()
        val hash = (ts + PRIVATE_API_KEY + PUBLIC_KEY).encryptMD5()
        val marvelDataDto = remoteService.getCharactersList(
            offset.toString(),
            LIMIT_CHARACTERS,
            ts,
            PUBLIC_KEY,
            hash
        )
       //devolver las lineas incertadas si son 0 quitar el cargando!!!
        if(marvelDataDto?.data?.offset?:0 > getCharacterList()?.size?:0){
            saveCharacterList(marvelDataDto?.data?.character?.map { it.toCharacterEntity() })
        }
    }
    private fun saveCharacterList(characterDtoList: List<CharacterEntity>?) {
        characterDtoList?.let { lisCharacter ->
            localService.insertAllCharacterList(lisCharacter)
        }

    }

    private fun getCharacterListFlow(): Flow<List<CharacterEntity>> {
        return localService.getMarvelDataEntityFlow()
    }

    private fun getCharacterList(): List<CharacterEntity>? {
        return localService.getMarvelDataEntity()
    }

    override suspend fun getCharacterByID(id: Int): Character? {
        val ts = Date().time.toString()
        val hash = (ts + PRIVATE_API_KEY + PUBLIC_KEY).encryptMD5()
        return remoteService.getCharacterById(
            id,
            ts,
            PUBLIC_KEY,
            hash
        )?.toMarvelData()?.data?.characterItem?.get(0)?.toCharacter()
    }
}
