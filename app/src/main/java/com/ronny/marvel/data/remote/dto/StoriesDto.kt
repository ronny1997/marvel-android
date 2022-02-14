package com.ronny.marvel.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.ronny.marvel.data.local.marvel.entity.StoriesEntity
import com.ronny.marvel.domain.model.Stories
import com.ronny.marvel.presentation.model.StoriesView

data class StoriesDto(
    @SerializedName("available")
    val available: Int = -1,
    @SerializedName("collectionURI")
    val collectionURI: String = "",
    @SerializedName("items")
    val items: List<ItemDto> = arrayListOf(),
    @SerializedName("returned")
    val returned: Int = -1
)

fun StoriesDto.toStoriesView(): StoriesView =
    StoriesView(available, collectionURI, items.map { it.toItemView() }, returned)
fun StoriesDto.toStories(): Stories =
    Stories(available, collectionURI, items.map { it.toItem() }, returned)
fun StoriesDto.toStoriesEntity(): StoriesEntity =
    StoriesEntity(0,available, collectionURI, items.map { it.toItemEntity() }, returned)