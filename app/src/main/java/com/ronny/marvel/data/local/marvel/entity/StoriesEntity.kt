package com.ronny.marvel.data.local.marvel.entity


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.ronny.marvel.domain.model.Stories
import com.ronny.marvel.presentation.model.StoriesView

@Entity(tableName = "StoriesEntity")
data class StoriesEntity(
    @SerializedName("available")
    val available: Int? = -1,
    @SerializedName("collectionURI")
    val collectionURI: String? = "",
    @SerializedName("items")
    val items: List<ItemEntity>? = arrayListOf(),
    @SerializedName("returned")
    val returned: Int? = -1
)

fun StoriesEntity.toStoriesView(): StoriesView =
    StoriesView(available, collectionURI, items?.map { it.toItemView() }, returned)
fun StoriesEntity.toStories(): Stories =
    Stories(available, collectionURI, items?.map { it.toItem() }, returned)