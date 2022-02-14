package com.ronny.marvel.data.local.marvel

import com.ronny.marvel.data.local.marvel.dao.MarvelDataDao
import com.ronny.marvel.data.local.marvel.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarvelLocalDataSourceImpl @Inject constructor(private val marvelDataDao: MarvelDataDao):MarvelLocalDataSource {

   override fun insertAllCharacterList(characterEntityList: List<CharacterEntity>) {
        marvelDataDao.insertListCharacter(characterEntityList)
    }

    override fun getMarvelDataEntityFlow(): Flow<List<CharacterEntity>> =
        marvelDataDao.getCharactersEntityFlow()

    override  fun getMarvelDataEntity(): List<CharacterEntity>? = marvelDataDao.getCharactersEntity()
    override  fun getCharacterByIdEntity(id: Int): CharacterEntity = marvelDataDao.getCharacterByIdEntity(id)

}