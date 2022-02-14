package com.ronny.marvel.data.local.marvel.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.ronny.marvel.domain.model.Item
import com.ronny.marvel.presentation.model.ItemView

@Entity(tableName = "ItemStoriesEntity")
data class ItemEntity(
    @PrimaryKey()
    var id: Int,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("name")
    val name: String
)

fun ItemEntity.toItemView(): ItemView = ItemView(resourceURI, name)
fun ItemEntity.toItem(): Item = Item(resourceURI, name)