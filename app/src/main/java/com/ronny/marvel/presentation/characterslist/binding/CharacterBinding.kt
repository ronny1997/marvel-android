package com.ronny.marvel.presentation.characterslist.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ronny.marvel.R
import com.ronny.marvel.presentation.charactersdetail.ItemsAdapter
import com.ronny.marvel.presentation.characterslist.CharacterAdapter
import com.ronny.marvel.presentation.model.CharactersListView
import com.ronny.marvel.presentation.model.ThumbnailView

object CharacterBinding {

    @BindingAdapter("app:coilUrl")
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