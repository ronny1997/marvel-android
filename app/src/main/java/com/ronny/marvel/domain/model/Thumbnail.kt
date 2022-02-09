package com.ronny.marvel.domain.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.ronny.marvel.presentation.model.ThumbnailView
import java.io.Serializable

@Keep
data class Thumbnail(
    val path: String? = "",
    val extension: String? = "",
): Serializable

fun Thumbnail.toThumbnailView(): ThumbnailView = ThumbnailView(path, extension)
fun Thumbnail.toThumbnail(): Thumbnail = Thumbnail(path, extension)