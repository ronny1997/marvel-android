package com.ronny.marvel.features.characters.model


import com.google.gson.annotations.SerializedName

data class ThumbnailDto(
    @SerializedName("path")
    val path: String? = "",
    @SerializedName("extension")
    val extension: String? = "",
)

fun ThumbnailDto.toThumbnailView():ThumbnailView = ThumbnailView(path, extension)