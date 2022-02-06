package com.ronny.marvel.features.characters.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class CharacterItemView(
    val id: Int? = -1,
    val name: String? = "",
    val description: String? = "",
    val modified: String? = "",
    val thumbnail: Thumbnail? = Thumbnail(),
    val resourceURI: String? = "",
    val comics: Comics? = Comics(),
    val series: Series? = Series(),
    val stories: Stories? = Stories(),
    val urls: List<Url>? = arrayListOf()
): Serializable