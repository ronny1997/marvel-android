package com.ronny.marvel.data.local.marvel.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ronny.marvel.data.local.marvel.entity.CharacterEntity
import com.ronny.marvel.data.local.marvel.entity.MarvelDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MarvelDataDao {

    @Insert
    abstract fun insertListCharacter(characterEntityList: List<CharacterEntity>)

    @Query("SELECT * FROM CharacterEntity")
    abstract fun getCharactersEntityFlow():  Flow<List<CharacterEntity>>

    @Query("SELECT * FROM CharacterEntity")
    abstract fun getCharactersEntity():  List<CharacterEntity>?

    @Query("SELECT * FROM CharacterEntity WHERE idCharacter = :id")
    abstract fun getCharacterByIdEntity(id: Int):  CharacterEntity


}