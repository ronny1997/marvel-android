package com.ronny.marvel.presentation.characterslist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ronny.marvel.common.extensions.getLayoutInflater
import com.ronny.marvel.databinding.CharactersItemBinding
import com.ronny.marvel.presentation.model.CharacterView
import com.ronny.marvel.presentation.model.MarvelDataView

class CharacterAdapter(private val listener: CharacterAdapterListener) :
    RecyclerView.Adapter<CharacterViewHolder>() {
    private val listCharacterItem: ArrayList<CharacterView> = arrayListOf()

    interface CharacterAdapterListener {
        fun clickListener(view: View, characterView: CharacterView?)
    }


    fun updateData(items: List<CharacterView>) {
        listCharacterItem.clear()
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
        holder.bind(listCharacterItem[position], listener)

    override fun getItemCount(): Int = listCharacterItem.size

}

class CharacterViewHolder(
    private val charactersItemBinding:
    CharactersItemBinding
) : RecyclerView.ViewHolder(charactersItemBinding.root) {
    fun bind(
        characterItem: CharacterView,
        listener: CharacterAdapter.CharacterAdapterListener
    ) {
        charactersItemBinding.characterView = characterItem
        charactersItemBinding.listener = listener
    }
}