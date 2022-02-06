package com.ronny.marvel.features.characters.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class ThumbnailView(
    val path: String? = "",
    val extension: String? = "",
): Serializable