package com.ronny.marvel.data.local.marvel

import com.ronny.marvel.data.local.marvel.dao.MarvelDataDao
import com.ronny.marvel.data.local.marvel.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface MarvelLocalDataSource {
    fun insertAllCharacterList(characterEntityList: List<CharacterEntity>)
    fun getMarvelDataEntityFlow(): Flow<List<CharacterEntity>>
    fun getMarvelDataEntity(): List<CharacterEntity>?
    fun getCharacterByIdEntity(id: Int): CharacterEntity

}