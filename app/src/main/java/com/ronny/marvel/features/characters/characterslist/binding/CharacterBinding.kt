package com.ronny.marvel.features.characters.characterslist.binding

import android.widget.GridView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ronny.marvel.features.characters.characterslist.GridViewCharacterAdapter
import com.ronny.marvel.features.characters.model.CharacterItem
import com.ronny.marvel.features.characters.model.Thumbnail
import com.squareup.picasso.Picasso

object CharacterBinding {

    @BindingAdapter("app:picasso_url")
    @JvmStatic
    fun url(view: ImageView, thumbnail: Thumbnail?) {
        Picasso.get()
            .load("${thumbnail?.path?.replace("http","https")}.${thumbnail?.extension}")
            .into(view)
    }

    @BindingAdapter("app:characters")
    @JvmStatic
    fun characters(view: GridView, listCharacterItem: List<CharacterItem>?) {
        if (view.adapter !is GridViewCharacterAdapter) {
            view.adapter = GridViewCharacterAdapter()
        }
        listCharacterItem?.let {
            (view.adapter as GridViewCharacterAdapter).updateData(it)
            (view.adapter as GridViewCharacterAdapter).notifyDataSetChanged()
        }
    }
}