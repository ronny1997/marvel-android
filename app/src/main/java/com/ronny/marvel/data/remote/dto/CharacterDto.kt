package com.ronny.marvel.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.ronny.marvel.data.local.marvel.entity.CharacterEntity
import com.ronny.marvel.domain.model.Character
import com.ronny.marvel.presentation.model.CharacterView

data class CharacterDto(
    @SerializedName("id")
    val id: Int = -1,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("modified")
    val modified: String = "",
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailDto = ThumbnailDto(),
    @SerializedName("resourceURI")
    val resourceURI: String = "",
    @SerializedName("comics")
    val comics: ComicsDto = ComicsDto(),
    @SerializedName("stories")
    val stories: StoriesDto = StoriesDto(),
)

fun CharacterDto.toCharacterView(): CharacterView = CharacterView(
    id,
    name,
    description,
    modified,
    thumbnail.toThumbnailView(),
    resourceURI,
    comics.toComicsView(),
    stories.toStoriesView(),
)
fun CharacterDto.toCharacter(): Character = Character(
    id,
    name,
    description,
    modified,
    thumbnail.toThumbnail(),
    resourceURI,
    comics.toComics(),
    stories.toStories(),
)
fun CharacterDto.toCharacterEntity(): CharacterEntity = CharacterEntity(
    0,
    id,
    name,
    description,
    modified,
    thumbnail.toThumbnailEntity(),
    resourceURI,
    comics.toComicsEntity(),
    stories.toStoriesEntity(),
)