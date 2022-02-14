package com.ronny.marvel.data.local.marvel.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.ronny.marvel.domain.model.Data
import com.ronny.marvel.presentation.model.DataView
import java.util.*

@Entity(tableName = "DataEntity")
data class DataEntity(
    @PrimaryKey()
    var id: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val characterItem: List<CharacterEntity>
){
    companion object {
        fun empty() = DataEntity(0,0, 0, 0, 0, listOf())
    }
}


fun DataEntity.toDataView(): DataView = DataView(offset, limit, total, count, characterItem.map { it.toCharacterView() })
fun DataEntity.toData(): Data = Data(offset, limit, total, count, characterItem.map { it.toCharacterItem() })