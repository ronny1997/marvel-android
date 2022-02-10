package com.ronny.marvel.data.local.marvel.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ronny.marvel.data.local.marvel.entity.DataEntity
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
}