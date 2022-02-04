package com.ronny.marvel.features.characters.model


import com.google.gson.annotations.SerializedName

data class CharacterItem(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("comics")
    val comics: Comics?,
    @SerializedName("series")
    val series: Series?,
    @SerializedName("stories")
    val stories: Stories?,
    @SerializedName("urls")
    val urls: List<Url>?
)