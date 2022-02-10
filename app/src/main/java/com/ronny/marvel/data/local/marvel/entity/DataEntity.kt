package com.ronny.marvel.data.local.marvel.entity


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.ronny.marvel.domain.model.Data
import com.ronny.marvel.presentation.model.DataView

@Entity(tableName = "DataEntity")
data class DataEntity(
    @SerializedName("offset")
    val offset: Int? = -1,
    @SerializedName("limit")
    val limit: Int? = -1,
    @SerializedName("total")
    val total: Int?= -1,
    @SerializedName("count")
    val count: Int?= -1,
    @SerializedName("results")
    val characterItem: List<CharacterEntity>? = listOf()
)

fun DataEntity.toDataView(): DataView = DataView(offset, limit, total, count, characterItem?.map { it.toCharacterView() })
fun DataEntity.toData(): Data = Data(offset, limit, total, count, characterItem?.map { it.toCharacterItem() })