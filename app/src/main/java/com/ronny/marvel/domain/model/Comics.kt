package com.ronny.marvel.domain.model

import androidx.annotation.Keep
import com.ronny.marvel.data.local.marvel.entity.ComicsEntity
import com.ronny.marvel.presentation.model.ComicsView
import java.io.Serializable

@Keep
data class Comics(
    val available: Int = -1,
    val collectionURI: String = "",
    val items: List<Item> = arrayListOf(),
    val returned: Int = -1
): Serializable

fun Comics.toComicsView(): ComicsView = ComicsView(available, collectionURI, items?.map { it.toItemView() },returned)
fun Comics.toComics(): Comics = Comics(available, collectionURI, items?.map { it.toItem() },returned)
fun Comics.toComicsEntity (): ComicsEntity = ComicsEntity (0,available, collectionURI, items.map { it.toItemEntity () },returned)