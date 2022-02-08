package com.ronny.marvel.features.characters.model

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class StoriesView(
    val available: Int? = -1,
    val collectionURI: String? = "",
    val items: List<ItemView>? = arrayListOf(),
    val returned: Int? = -1
): Serializable