package com.ronny.marvel.features

import com.ronny.marvel.common.util.Failure
import com.ronny.marvel.common.util.Resource
import com.ronny.marvel.data.local.marvel.entity.CharacterEntity
import com.ronny.marvel.data.remote.dto.MarvelDataDto
import com.ronny.marvel.domain.model.Character
import com.ronny.marvel.domain.model.MarvelData
import com.ronny.marvel.domain.repository.CharacterRepository
import com.ronny.marvel.presentation.model.MarvelDataView
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryTest : CharacterRepository {
    var charactersListDto = MarvelDataDto()
    override fun getCharacter(): Flow<List<CharacterEntity>> {
        TODO("Not yet implemented")
    }

    override fun getCharacterRemote(
        offset: Int,
        ts: String,
        hash: String
    ): Resource<Failure, MarvelData> {
        TODO("Not yet implemented")
    }

    override fun getCharacterList(): List<CharacterEntity>? {
        TODO("Not yet implemented")
    }

    override fun saveCharacterList(characterDtoList: List<Character>?) {
        TODO("Not yet implemented")
    }

    override fun getCharacterByID(id: Int): Character? {
        TODO("Not yet implemented")
    }

}