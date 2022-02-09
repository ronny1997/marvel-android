package com.ronny.marvel.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.ronny.marvel.domain.model.Data
import com.ronny.marvel.presentation.model.DataView

data class DataDto(
    @SerializedName("offset")
    val offset: Int? = -1,
    @SerializedName("limit")
    val limit: Int? = -1,
    @SerializedName("total")
    val total: Int?= -1,
    @SerializedName("count")
    val count: Int?= -1,
    @SerializedName("results")
    val characterItem: List<CharacterItemDto>? = listOf()
)

fun DataDto.toDataView(): DataView = DataView(offset, limit, total, count, characterItem?.map { it.toCharacterItemView() })
fun DataDto.toData(): Data = Data(offset, limit, total, count, characterItem?.map { it.toCharacterItem() })