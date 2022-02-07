package com.ronny.marvel.features.characters.characterslist.binding

import android.icu.number.Scale
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.ronny.marvel.R
import com.ronny.marvel.features.characters.characterslist.CharacterAdapter
import com.ronny.marvel.features.characters.model.CharacterItemView
import com.ronny.marvel.features.characters.model.CharactersListView
import com.ronny.marvel.features.characters.model.ThumbnailView

object CharacterBinding {

    @BindingAdapter("app:coil_url")
    @JvmStatic
    fun coilUrl(view: ImageView, thumbnail: ThumbnailView?) {
        view.load("${thumbnail?.path?.replace("http", "https")}.${thumbnail?.extension}") {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
        }
    }

    @BindingAdapter("app:characters")
    @JvmStatic
    fun characters(view: RecyclerView, listCharacterItem: CharactersListView?) {
        listCharacterItem?.let {
            (view.adapter as CharacterAdapter).updateData(it)
        }
    }
}