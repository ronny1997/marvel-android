package com.ronny.marvel.features.characters.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("offset")
    val offset: Int? = -1,
    @SerializedName("limit")
    val limit: Int? = -1,
    @SerializedName("total")
    val total: Int?= -1,
    @SerializedName("count")
    val count: Int?= -1,
    @SerializedName("results")
    val characterItem: List<CharacterItem>? = listOf()
)