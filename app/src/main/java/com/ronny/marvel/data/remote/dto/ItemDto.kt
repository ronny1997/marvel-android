package com.ronny.marvel.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.ronny.marvel.domain.model.Item
import com.ronny.marvel.presentation.model.ItemView

data class ItemDto(
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("name")
    val name: String?
)

fun ItemDto.toItemView(): ItemView = ItemView(resourceURI,name)
fun ItemDto.toItem(): Item = Item(resourceURI,name)