package com.ronny.marvel.domain.model

import androidx.annotation.Keep
import com.ronny.marvel.presentation.model.StoriesView
import java.io.Serializable

@Keep
data class Stories(
    val available: Int? = -1,
    val collectionURI: String? = "",
    val items: List<Item>? = arrayListOf(),
    val returned: Int? = -1
) : Serializable

fun Stories.toStoriesView(): StoriesView = StoriesView(available, collectionURI, items?.map { it.toItemView() }, returned)
fun Stories.toStories(): Stories = Stories(available, collectionURI, items?.map { it.toItem() }, returned)