package com.ronny.marvel.features.characters.characterslist

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.ronny.marvel.R
import com.ronny.marvel.core.extensions.getLayoutInflater
import com.ronny.marvel.databinding.CharactersItemBinding
import com.ronny.marvel.features.characters.model.CharacterItem
import com.squareup.picasso.Picasso

class GridViewCharacterAdapter : BaseAdapter() {
    private val listCharacterItem: ArrayList<CharacterItem> = arrayListOf()

    fun updateData(items: List<CharacterItem> = arrayListOf()) {
        listCharacterItem.addAll(items)
    }

    override fun getCount(): Int = listCharacterItem.size

    override fun getItem(position: Int): Any? = null

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var mConvertView: View? = null
        convertView ?: run {
            val charactersItemBinding = bind(position, parent)
            mConvertView = charactersItemBinding.root
        }
        return mConvertView
    }

    private fun bind(position: Int, parent: ViewGroup): CharactersItemBinding {
        val charactersItemBinding =
            CharactersItemBinding.inflate(parent.getLayoutInflater(), parent, false)
        charactersItemBinding.characterItem = listCharacterItem[position]
        return charactersItemBinding
    }
}

class CharacterViewHolder(private val binding: CharactersItemBinding) {

}