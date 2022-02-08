package com.ronny.marvel.features.characters.model


import com.google.gson.annotations.SerializedName

data class ComicsDto(
    @SerializedName("available")
    val available: Int? = -1,
    @SerializedName("collectionURI")
    val collectionURI: String? = "",
    @SerializedName("items")
    val items: List<ItemDto>? = arrayListOf(),
    @SerializedName("returned")
    val returned: Int? = -1
)

fun ComicsDto.toComicsView(): ComicsView = ComicsView(available, collectionURI, items?.map { it.toItemView() }, returned)