package com.ronny.marvel.data.local.marvel.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ronny.marvel.data.local.marvel.entity.MarvelDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MarvelDataDao {

    @Insert
    abstract fun insertAllMarvelData(marvelDataEntityList: List<MarvelDataEntity>)

    @Query("SELECT * FROM MarvelDataEntity")
    abstract fun getMarvelDataEntity(): Flow<MarvelDataEntity>

}