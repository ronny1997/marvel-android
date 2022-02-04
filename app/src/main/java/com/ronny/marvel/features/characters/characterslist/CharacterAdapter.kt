package com.ronny.marvel.features.characters.characterslist

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ronny.marvel.R
import com.ronny.marvel.core.extensions.getLayoutInflater
import com.ronny.marvel.databinding.CharactersItemBinding
import com.ronny.marvel.features.characters.model.CharacterItem
import com.squareup.picasso.Picasso
import okhttp3.internal.notifyAll

class CharacterAdapter : RecyclerView.Adapter<CharacterViewHolder>() {
    private val listCharacterItem: ArrayList<CharacterItem> = arrayListOf()

    fun updateData(items: List<CharacterItem> = arrayListOf()) {
        listCharacterItem.addAll(items)
        this.notifyItemInserted(listCharacterItem.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(
            CharactersItemBinding.inflate(
                parent.getLayoutInflater(),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(listCharacterItem[position])

    override fun getItemCount(): Int = listCharacterItem.size

}

class CharacterViewHolder(
    private val charactersItemBinding:
    CharactersItemBinding
) : RecyclerView.ViewHolder(charactersItemBinding.root) {
    fun bind(characterItem: CharacterItem) {
        charactersItemBinding.characterItem = characterItem
    }
}