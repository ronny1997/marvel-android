package com.ronny.marvel.features.characters.characterslist.binding

import android.widget.GridView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ronny.marvel.features.characters.characterslist.CharacterAdapter
import com.ronny.marvel.features.characters.model.CharacterItem
import com.ronny.marvel.features.characters.model.Thumbnail
import com.squareup.picasso.Picasso
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

object CharacterBinding {

    @BindingAdapter("app:picasso_url")
    @JvmStatic
    fun url(view: ImageView, thumbnail: Thumbnail?) {
        runBlocking {
            coroutineScope {
                Picasso.get()
                    .load("${thumbnail?.path?.replace("http","https")}.${thumbnail?.extension}")
                    .into(view)
            }
        }
    }

    @BindingAdapter("app:characters")
    @JvmStatic
    fun characters(view: RecyclerView, listCharacterItem: List<CharacterItem>?) {
        if (view.adapter !is CharacterAdapter) {
            view.adapter = CharacterAdapter()
        }
        listCharacterItem?.let {
            (view.adapter as CharacterAdapter).updateData(it)
        }
    }
}