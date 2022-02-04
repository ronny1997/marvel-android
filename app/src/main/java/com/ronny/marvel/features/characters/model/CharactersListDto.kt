package com.ronny.marvel.features.characters.model


import com.google.gson.annotations.SerializedName

data class CharactersListDto(
    @SerializedName("code")
    val code: Int?= -1,
    @SerializedName("status")
    val status: String?="",
    @SerializedName("copyright")
    val copyright: String?="",
    @SerializedName("attributionText")
    val attributionText: String?="",
    @SerializedName("attributionHTML")
    val attributionHTML: String?="",
    @SerializedName("etag")
    val etag: String?="",
    @SerializedName("data")
    val data: Data? = Data()
)