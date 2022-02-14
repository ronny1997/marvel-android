package com.ronny.marvel.data.local.marvel.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.ronny.marvel.domain.model.Comics
import com.ronny.marvel.presentation.model.ComicsView

@Entity(tableName = "ComicsEntity")
data class ComicsEntity(
    @PrimaryKey()
    var id: Int,
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<ItemEntity>,
    @SerializedName("returned")
    val returned: Int
) {
    companion object {
        fun empty() = ComicsEntity(0, 0, "", listOf(), 0)
    }
}

fun ComicsEntity.toComicsView(): ComicsView =
    ComicsView(available, collectionURI, items.map { it.toItemView() }, returned)

fun ComicsEntity.toComics(): Comics =
    Comics(available, collectionURI, items.map { it.toItem() }, returned)