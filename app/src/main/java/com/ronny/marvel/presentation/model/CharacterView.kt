package com.ronny.marvel.presentation.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class CharacterView(
    val id: Int? = -1,
    val name: String? = "",
    val description: String? = "",
    val modified: String? = "",
    val thumbnail: ThumbnailView? = ThumbnailView(),
    val resourceURI: String? = "",
    val comics: ComicsView? = ComicsView(),
    val stories: StoriesView? = StoriesView(),
) : Parcelable