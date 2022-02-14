package com.ronny.marvel.data.local.marvel.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ronny.marvel.data.local.marvel.entity.*
import java.lang.reflect.Type

class DataConverter {

    @TypeConverter
    fun dataEntityToGson(dataEntity: DataEntity): String {
        val gson = Gson()
        val type: Type = object : TypeToken<DataEntity>() {}.type
        return gson.toJson(dataEntity, type)
    }

    @TypeConverter
    fun gsonToDataEntity(dataEntity: String): DataEntity {
        val gson = Gson()
        val type: Type = object : TypeToken<DataEntity>() {}.type
        return gson.fromJson(dataEntity, type)
    }

    @TypeConverter
    fun thumbnailEntityToGson(thumbnailEntity: ThumbnailEntity): String {
        val gson = Gson()
        val type: Type = object : TypeToken<ThumbnailEntity>() {}.type
        return gson.toJson(thumbnailEntity, type)
    }

    @TypeConverter
    fun gsonToThumbnailEntity(thumbnailEntity: String): ThumbnailEntity {
        val gson = Gson()
        val type: Type = object : TypeToken<ThumbnailEntity>() {}.type
        return gson.fromJson(thumbnailEntity, type)
    }

    @TypeConverter
    fun comicsEntityToGson(comics: ComicsEntity): String {
        val gson = Gson()
        val type: Type = object : TypeToken<ComicsEntity>() {}.type
        return gson.toJson(comics, type)
    }

    @TypeConverter
    fun gsonToComicsEntity(comics: String): ComicsEntity {
        val gson = Gson()
        val type: Type = object : TypeToken<ComicsEntity>() {}.type
        return gson.fromJson(comics, type)
    }

    @TypeConverter
    fun storiesEntityToGson(storiesEntity: StoriesEntity): String {
        val gson = Gson()
        val type: Type = object : TypeToken<StoriesEntity>() {}.type
        return gson.toJson(storiesEntity, type)
    }

    @TypeConverter
    fun gsonToStoriesEntity(storiesEntity: String): StoriesEntity {
        val gson = Gson()
        val type: Type = object : TypeToken<StoriesEntity>() {}.type
        return gson.fromJson(storiesEntity, type)
    }

    @TypeConverter
    fun characterEntityToGson(characterEntity: CharacterEntity): String {
        val gson = Gson()
        val type: Type = object : TypeToken<CharacterEntity>() {}.type
        return gson.toJson(characterEntity, type)
    }

    @TypeConverter
    fun gsonToCharacterEntity(characterEntity: String): CharacterEntity {
        val gson = Gson()
        val type: Type = object : TypeToken<CharacterEntity>() {}.type
        return gson.fromJson(characterEntity, type)
    }
    @TypeConverter
    fun listItemStoriesEntityToGson(itemEntityList: List<ItemEntity>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<ItemEntity>>() {}.type
        return gson.toJson(itemEntityList, type)
    }

    @TypeConverter
    fun gsonToListItemStoriesEntity(itemEntityList: String):  List<ItemEntity> {
        val gson = Gson()
        val type: Type = object : TypeToken< List<ItemEntity>>() {}.type
        return gson.fromJson(itemEntityList, type)
    }

    @TypeConverter
    fun listCharacterEntityToGson(characterEntity: List<CharacterEntity>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<CharacterEntity>>() {}.type
        return gson.toJson(characterEntity, type)
    }

    @TypeConverter
    fun gsonToListCharacterEntity(characterEntity: String):  List<CharacterEntity> {
        val gson = Gson()
        val type: Type = object : TypeToken< List<CharacterEntity>>() {}.type
        return gson.fromJson(characterEntity, type)
    }
}