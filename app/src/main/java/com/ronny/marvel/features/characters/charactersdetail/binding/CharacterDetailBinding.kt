package com.ronny.marvel.features.characters.charactersdetail.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ronny.marvel.features.characters.charactersdetail.ItemsAdapter
import com.ronny.marvel.features.characters.model.ComicsView
import com.ronny.marvel.features.characters.model.ItemView

object CharacterDetailBinding {

    @BindingAdapter("app:items_view")
    @JvmStatic
    fun itemView(view: RecyclerView, items:  List<ItemView>?) {
        if (view.adapter !is ItemsAdapter) {
            view.adapter = ItemsAdapter()
        }
        items?.let {
            (view.adapter as ItemsAdapter).updateData(it)
        }
    }
}