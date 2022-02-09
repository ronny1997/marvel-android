package com.ronny.marvel.presentation.characterslist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ronny.marvel.common.extensions.getLayoutInflater
import com.ronny.marvel.databinding.CharactersItemBinding
import com.ronny.marvel.presentation.model.CharacterItemView
import com.ronny.marvel.presentation.model.CharactersListView

class CharacterAdapter(private val listener: CharacterAdapterListener) :
    RecyclerView.Adapter<CharacterViewHolder>() {
    private var etag = ""
    private val listCharacterItem: ArrayList<CharacterItemView> = arrayListOf()

    interface CharacterAdapterListener {
        fun clickListener(view: View, characterItemView: CharacterItemView?)
    }


    fun updateData(items: CharactersListView) {
        if (items.etag != etag) {
            items.etag?.let {
                etag = it
            }
            items.data?.characterItem?.let {
                listCharacterItem.addAll(ArrayList(it))
            }

            this.notifyItemInserted(listCharacterItem.size)
        }

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
        characterItem: CharacterItemView,
        listener: CharacterAdapter.CharacterAdapterListener
    ) {
        charactersItemBinding.characterItemView = characterItem
        charactersItemBinding.listener = listener
    }
}