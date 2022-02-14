package com.ronny.marvel.data.local.marvel.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.ronny.marvel.domain.model.Thumbnail
import com.ronny.marvel.presentation.model.ThumbnailView
import java.util.*

@Entity(tableName = "ThumbnailEntity")
data class ThumbnailEntity(
    @PrimaryKey()
    var id: Int,
    @SerializedName("path")
    val path: String ,
    @SerializedName("extension")
    val extension: String ,
) {

    companion object {
        fun empty() = ThumbnailEntity(0,"", "")
    }
}

fun ThumbnailEntity.toThumbnailView(): ThumbnailView = ThumbnailView(path, extension)
fun ThumbnailEntity.toThumbnail(): Thumbnail = Thumbnail(path, extension)