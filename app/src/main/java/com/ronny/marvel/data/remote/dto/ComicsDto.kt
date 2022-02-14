
package com.ronny.marvel.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.ronny.marvel.data.local.marvel.entity.ComicsEntity
import com.ronny.marvel.domain.model.Comics
import com.ronny.marvel.presentation.model.ComicsView

data class ComicsDto(
    @SerializedName("available")
    val available: Int = -1,
    @SerializedName("collectionURI")
    val collectionURI: String = "",
    @SerializedName("items")
    val items: List<ItemDto> = arrayListOf(),
    @SerializedName("returned")
    val returned: Int = -1
)

fun ComicsDto.toComicsView(): ComicsView = ComicsView(available, collectionURI, items.map { it.toItemView() }, returned)
fun ComicsDto.toComics(): Comics = Comics(available, collectionURI, items.map { it.toItem() }, returned)
fun ComicsDto.toComicsEntity(): ComicsEntity = ComicsEntity(0, available, collectionURI, items.map { it.toItemEntity() }, returned)