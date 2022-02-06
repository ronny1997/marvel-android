package com.ronny.marvel.features.characters.model


import com.google.gson.annotations.SerializedName

data class CharacterItemDto(
    @SerializedName("id")
    val id: Int? = -1,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailDto? = ThumbnailDto(),
    @SerializedName("resourceURI")
    val resourceURI: String? = "",
    @SerializedName("comics")
    val comics: ComicsDto? = ComicsDto(),
    @SerializedName("stories")
    val stories: StoriesDto? = StoriesDto(),
)

fun CharacterItemDto.toCharacterItemView(): CharacterItemView = CharacterItemView(
    id,
    name,
    description,
    modified,
    thumbnail?.toThumbnailView(),
    resourceURI,
    comics?.toComicsView(),
    stories?.toStoriesView(),
)