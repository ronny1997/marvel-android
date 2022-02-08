package com.ronny.marvel.features.characters.model


import com.google.gson.annotations.SerializedName

data class ItemDto(
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("name")
    val name: String?
)

fun ItemDto.toItemView(): ItemView = ItemView(resourceURI,name)