package com.ronny.marvel.presentation.model

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class CharacterItemView(
    val id: Int? = -1,
    val name: String? = "",
    val description: String? = "",
    val modified: String? = "",
    val thumbnail: ThumbnailView? = ThumbnailView(),
    val resourceURI: String? = "",
    val comics: ComicsView? = ComicsView(),
    val stories: StoriesView? = StoriesView(),
): Serializable