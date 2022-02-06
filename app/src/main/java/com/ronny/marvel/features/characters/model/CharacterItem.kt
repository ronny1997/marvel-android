package com.ronny.marvel.features.characters.model


import com.google.gson.annotations.SerializedName

data class CharacterItem(
    @SerializedName("id")
    val id: Int? = -1,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("resourceURI")
    val resourceURI: String? = "",
    @SerializedName("comics")
    val comics: Comics? = Comics(),
    @SerializedName("series")
    val series: Series? = Series(),
    @SerializedName("stories")
    val stories: Stories? = Stories(),
    @SerializedName("urls")
    val urls: List<Url>? = arrayListOf()
)

fun CharacterItem.toCharacterItemView(): CharacterItemView = CharacterItemView(
    id,
    name,
    description,
    modified,
    thumbnail,
    resourceURI,
    comics,
    series,
    stories,
    urls
)