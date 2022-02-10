package com.ronny.marvel.data.local.marvel.entity


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.ronny.marvel.domain.model.Thumbnail
import com.ronny.marvel.presentation.model.ThumbnailView

@Entity(tableName = "ThumbnailEntity")
data class ThumbnailEntity(
    @SerializedName("path")
    val path: String? = "",
    @SerializedName("extension")
    val extension: String? = "",
)

fun ThumbnailEntity.toThumbnailView(): ThumbnailView = ThumbnailView(path, extension)
fun ThumbnailEntity.toThumbnail(): Thumbnail = Thumbnail(path, extension)