package com.ronny.marvel.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.ronny.marvel.domain.model.CharactersList
import com.ronny.marvel.presentation.model.CharactersListView

data class CharactersListDto(
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
    val data: DataDto? = DataDto()
)

fun CharactersListDto.toCharactersListView(): CharactersListView = CharactersListView(
    code,
    status,
    copyright,
    attributionText,
    attributionHTML,
    etag,
    data?.toDataView()
)
fun CharactersListDto.toCharactersList(): CharactersList = CharactersList(
    code,
    status,
    copyright,
    attributionText,
    attributionHTML,
    etag,
    data?.toData()
)