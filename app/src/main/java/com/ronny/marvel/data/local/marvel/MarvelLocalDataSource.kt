package com.ronny.marvel.data.local

import com.ronny.marvel.data.local.marvel.dao.MarvelDataDao
import com.ronny.marvel.data.local.marvel.entity.MarvelDataEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarvelLocalDataSource @Inject constructor(private val marvelDataDao: MarvelDataDao) {

    fun insertAllMarvelData(marvelDataEntityList: List<MarvelDataEntity>) {
        marvelDataDao.insertAllMarvelData(marvelDataEntityList)
    }

    fun getMarvelDataEntity(): Flow<MarvelDataEntity> = marvelDataDao.getMarvelDataEntity()

}