package com.ronny.marvel.data.local.marvel.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.ronny.marvel.domain.model.Character
import com.ronny.marvel.presentation.model.CharacterView

@Entity(tableName = "CharacterEntity")
data class CharacterEntity(
    @PrimaryKey()
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailEntity,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("comics")
    val comics: ComicsEntity,
    @SerializedName("stories")
    val stories: StoriesEntity,
) {
    companion object {
        fun empty() = CharacterEntity(
            0,
            "",
            "",
            "",
            ThumbnailEntity.empty(),
            "",
            ComicsEntity.empty(),
            StoriesEntity.empty()
        )
    }
}


fun CharacterEntity.toCharacterView(): CharacterView = CharacterView(
    id,
    name,
    description,
    modified,
    thumbnail.toThumbnailView(),
    resourceURI,
    comics.toComicsView(),
    stories.toStoriesView(),
)

fun CharacterEntity.toCharacterItem(): Character =
    Character(
        id,
        name,
        description,
        modified,
        thumbnail.toThumbnail(),
        resourceURI,
        comics.toComics(),
        stories.toStories(),
    )