package com.ronny.marvel.presentation.charactersdetail.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ronny.marvel.presentation.charactersdetail.ItemsAdapter
import com.ronny.marvel.presentation.model.ItemView

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