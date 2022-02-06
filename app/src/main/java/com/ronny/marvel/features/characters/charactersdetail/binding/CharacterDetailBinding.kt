package com.ronny.marvel.features.characters.charactersdetail.binding

import android.icu.number.Scale
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.ronny.marvel.R
import com.ronny.marvel.features.characters.charactersdetail.ItemsAdapter
import com.ronny.marvel.features.characters.characterslist.CharacterAdapter
import com.ronny.marvel.features.characters.model.CharacterItemView
import com.ronny.marvel.features.characters.model.CharactersListView
import com.ronny.marvel.features.characters.model.ComicsView
import com.ronny.marvel.features.characters.model.ThumbnailView

object CharacterDetailBinding {

    @BindingAdapter("app:comics_items")
    @JvmStatic
    fun comics(view: RecyclerView, items: ComicsView?) {
        if (view.adapter !is ItemsAdapter) {
            view.adapter = ItemsAdapter()
        }
        items?.let {
            (view.adapter as ItemsAdapter).updateData(items)
        }
    }
}