package com.ronny.marvel.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.ronny.marvel.data.local.marvel.entity.MarvelDataEntity
import com.ronny.marvel.domain.model.MarvelData
import com.ronny.marvel.presentation.model.MarvelDataView

data class MarvelDataDto(
    @SerializedName("id")
    val id: Int = -1,
    @SerializedName("code")
    val code: Int = -1,
    @SerializedName("status")
    val status: String = "",
    @SerializedName("copyright")
    val copyright: String = "",
    @SerializedName("attributionText")
    val attributionText: String = "",
    @SerializedName("attributionHTML")
    val attributionHTML: String = "",
    @SerializedName("etag")
    val etag: String = "",
    @SerializedName("data")
    val data: DataDto = DataDto()
)

fun MarvelDataDto.toMarvelDataView(): MarvelDataView = MarvelDataView(
    code,
    status,
    copyright,
    attributionText,
    attributionHTML,
    etag,
    data.toDataView()
)
fun MarvelDataDto.toMarvelData(): MarvelData = MarvelData(
    code,
    status,
    copyright,
    attributionText,
    attributionHTML,
    etag,
    data.toData()
)
fun MarvelDataDto.toMarvelDataEntity(): MarvelDataEntity = MarvelDataEntity(
    id,
    code,
    status,
    copyright,
    attributionText,
    attributionHTML,
    etag,
    data.toDataEntity()
)