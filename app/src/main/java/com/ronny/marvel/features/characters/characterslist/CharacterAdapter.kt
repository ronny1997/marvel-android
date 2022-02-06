package com.ronny.marvel.features.characters.characterslist

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ronny.marvel.core.extensions.getLayoutInflater
import com.ronny.marvel.databinding.CharactersItemBinding
import com.ronny.marvel.features.characters.model.CharacterItemDto
import com.ronny.marvel.features.characters.model.CharacterItemView
import com.ronny.marvel.features.characters.model.CharactersListView

class CharacterAdapter : RecyclerView.Adapter<CharacterViewHolder>() {
    private var etag = ""
    private val listCharacterItem: ArrayList<CharacterItemView> = arrayListOf()
    var clickListener: (ImageView, CharacterItemView?) -> Unit = { _, _ -> }

    fun updateData(items: CharactersListView) {
        if( items.etag != etag){
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
        holder.bind(listCharacterItem[position], clickListener)

    override fun getItemCount(): Int = listCharacterItem.size

}

class CharacterViewHolder(
    private val charactersItemBinding:
    CharactersItemBinding
) : RecyclerView.ViewHolder(charactersItemBinding.root) {
    fun bind(
        characterItem: CharacterItemView,
        clickListener: (ImageView, CharacterItemView?) -> Unit
    ) {
        charactersItemBinding.characterItem = characterItem
        charactersItemBinding.imvCharacters.setOnClickListener {
            clickListener(
                charactersItemBinding.imvCharacters,
                characterItem
            )
        }
    }
}