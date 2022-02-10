
package com.ronny.marvel.data.local.marvel.entity


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.ronny.marvel.domain.model.Comics
import com.ronny.marvel.presentation.model.ComicsView

@Entity(tableName = "ComicsEntity")
data class ComicsEntity(
    @SerializedName("available")
    val available: Int? = -1,
    @SerializedName("collectionURI")
    val collectionURI: String? = "",
    @SerializedName("items")
    val items: List<ItemEntity>? = arrayListOf(),
    @SerializedName("returned")
    val returned: Int? = -1
)

fun ComicsEntity.toComicsView(): ComicsView = ComicsView(available, collectionURI, items?.map { it.toItemView() }, returned)
fun ComicsEntity.toComics(): Comics = Comics(available, collectionURI, items?.map { it.toItem() }, returned)