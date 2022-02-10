package com.ronny.marvel.data.local.marvel.entity


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.ronny.marvel.domain.model.CharactersList
import com.ronny.marvel.presentation.model.MarvelDataView

@Entity(tableName = "MarvelDataEntity")
data class MarvelDataEntity(
    @SerializedName("code")
    val code: Int? = -1,
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("copyright")
    val copyright: String? = "",
    @SerializedName("attributionText")
    val attributionText: String? = "",
    @SerializedName("attributionHTML")
    val attributionHTML: String? = "",
    @SerializedName("etag")
    val etag: String? = "",
    @SerializedName("data")
    val data: DataEntity? = DataEntity()
)

fun MarvelDataEntity.toCharactersListView(): MarvelDataView = MarvelDataView(
    code,
    status,
    copyright,
    attributionText,
    attributionHTML,
    etag,
    data?.toDataView()
)
fun MarvelDataEntity.toCharactersList(): CharactersList = CharactersList(
    code,
    status,
    copyright,
    attributionText,
    attributionHTML,
    etag,
    data?.toData()
)