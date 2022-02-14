package com.ronny.marvel.domain.model


import androidx.annotation.Keep
import com.ronny.marvel.presentation.model.CharacterView
import java.io.Serializable

@Keep
data class Character(
    val id: Int? = -1,
    val name: String? = "",
    val description: String? = "",
    val modified: String? = "",
    val thumbnail: Thumbnail? = Thumbnail(),
    val resourceURI: String? = "",
    val comics: Comics? = Comics(),
    val stories: Stories? = Stories(),
): Serializable

fun Character.toCharacterView(): CharacterView = CharacterView(id, name, description, modified, thumbnail?.toThumbnailView(), resourceURI, comics?.toComicsView(), stories?.toStoriesView())
fun Character.toCharacter(): Character =
    Character(id, name, description, modified, thumbnail?.toThumbnail(), resourceURI, comics?.toComics(), stories?.toStories())