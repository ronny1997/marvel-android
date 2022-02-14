package com.ronny.marvel.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.ronny.marvel.data.local.marvel.entity.ThumbnailEntity
import com.ronny.marvel.domain.model.Thumbnail
import com.ronny.marvel.presentation.model.ThumbnailView

data class ThumbnailDto(
    @SerializedName("path")
    val path: String = "",
    @SerializedName("extension")
    val extension: String = "",
)

fun ThumbnailDto.toThumbnailView(): ThumbnailView = ThumbnailView(path, extension)
fun ThumbnailDto.toThumbnail(): Thumbnail = Thumbnail(path, extension)
fun ThumbnailDto.toThumbnailEntity(): ThumbnailEntity = ThumbnailEntity(0, path, extension)
