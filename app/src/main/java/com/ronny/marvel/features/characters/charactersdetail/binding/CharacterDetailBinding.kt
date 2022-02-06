package com.ronny.marvel.features.characters.charactersdetail.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ronny.marvel.features.characters.charactersdetail.ItemsAdapter
import com.ronny.marvel.features.characters.model.ComicsView

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