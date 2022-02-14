package com.ronny.marvel.data.local

import com.ronny.marvel.data.local.marvel.dao.MarvelDataDao
import com.ronny.marvel.data.local.marvel.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarvelLocalDataSource @Inject constructor(private val marvelDataDao: MarvelDataDao) {

    fun insertAllCharacterList(characterEntityList: List<CharacterEntity>) {
        marvelDataDao.insertListCharacter(characterEntityList)
    }

    fun getMarvelDataEntityFlow(): Flow<List<CharacterEntity>> = marvelDataDao.getCharacterEntityFlow()
    fun getMarvelDataEntity(): List<CharacterEntity>? = marvelDataDao.getCharacterEntity()

}