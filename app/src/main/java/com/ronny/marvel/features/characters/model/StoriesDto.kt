package com.ronny.marvel.features.characters.model


import com.google.gson.annotations.SerializedName

data class StoriesDto(
    @SerializedName("available")
    val available: Int? = -1,
    @SerializedName("collectionURI")
    val collectionURI: String? = "",
    @SerializedName("items")
    val items: List<ItemDto>? = arrayListOf(),
    @SerializedName("returned")
    val returned: Int? = -1
)

fun StoriesDto.toStoriesView(): StoriesView =
    StoriesView(available, collectionURI, items?.map { it.toItemView() }, returned)