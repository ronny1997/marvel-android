package com.ronny.marvel.data.repository


import com.ronny.marvel.common.constans.Constants.LIMIT_CHARACTERS
import com.ronny.marvel.common.constans.Constants.PUBLIC_KEY
import com.ronny.marvel.common.util.NetworkHandler
import com.ronny.marvel.common.util.Resource
import com.ronny.marvel.common.util.request
import com.ronny.marvel.common.util.Failure
import com.ronny.marvel.data.local.marvel.MarvelLocalDataSource
import com.ronny.marvel.data.local.marvel.entity.CharacterEntity
import com.ronny.marvel.data.local.marvel.entity.toCharacterItem
import com.ronny.marvel.data.remote.MarvelRemoteDataSource
import com.ronny.marvel.data.remote.dto.MarvelDataDto
import com.ronny.marvel.data.remote.dto.toMarvelData
import com.ronny.marvel.domain.model.Character
import com.ronny.marvel.domain.model.MarvelData
import com.ronny.marvel.domain.model.toCharacterEntity
import com.ronny.marvel.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val marvelRemoteDataSource: MarvelRemoteDataSource,
    private val localServiceImpl: MarvelLocalDataSource,
    private val networkHandler: NetworkHandler
) : CharacterRepository {

    override fun getCharacter(
    ): Flow<List<CharacterEntity>> = localServiceImpl.getMarvelDataEntityFlow()

    override fun getCharacterRemote(
        offset: Int,
        ts: String,
        hash: String
    ): Resource<Failure, MarvelData> = request(
        networkHandler, marvelRemoteDataSource.getCharactersList(
            offset.toString(),
            LIMIT_CHARACTERS,
            ts,
            PUBLIC_KEY,
            hash
        ), {
            it.toMarvelData() ?: MarvelData()
        }, MarvelDataDto()
    )


    override fun getCharacterList(): List<CharacterEntity>? = localServiceImpl.getMarvelDataEntity()


    override fun saveCharacterList(characterDtoList: List<Character>?) {
        characterDtoList?.let { lisCharacter ->
            localServiceImpl.insertAllCharacterList(lisCharacter.map { it.toCharacterEntity() })
        }
    }


    override fun getCharacterByID(id: Int): Character =
        localServiceImpl.getCharacterByIdEntity(id).toCharacterItem()

}
