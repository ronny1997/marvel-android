package com.ronny.marvel.data.local.marvel.entity


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.ronny.marvel.domain.model.CharacterItem
import com.ronny.marvel.presentation.model.CharacterView

@Entity(tableName = "CharacterEntity")
data class CharacterEntity(
    @SerializedName("id")
    val id: Int? = -1,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailEntity? = ThumbnailEntity(),
    @SerializedName("resourceURI")
    val resourceURI: String? = "",
    @SerializedName("comics")
    val comics: ComicsEntity? = ComicsEntity(),
    @SerializedName("stories")
    val stories: StoriesEntity? = StoriesEntity(),
)

fun CharacterEntity.toCharacterView(): CharacterView = CharacterView(
    id,
    name,
    description,
    modified,
    thumbnail?.toThumbnailView(),
    resourceURI,
    comics?.toComicsView(),
    stories?.toStoriesView(),
)
fun CharacterEntity.toCharacterItem(): CharacterItem = CharacterItem(
    id,
    name,
    description,
    modified,
    thumbnail?.toThumbnail(),
    resourceURI,
    comics?.toComics(),
    stories?.toStories(),
)