package com.ronny.marvel.features.characters.charactersdetail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ronny.marvel.core.extensions.getLayoutInflater
import com.ronny.marvel.databinding.ComicsItemsBinding
import com.ronny.marvel.features.characters.model.ComicsView
import com.ronny.marvel.features.characters.model.ItemView

class ItemsAdapter : RecyclerView.Adapter<ComicsItemsBindingViewHolder>() {
    private val itemViewList: ArrayList<ItemView> = arrayListOf()

    fun updateData(items: ComicsView) {
            items.items?.let {
                itemViewList.addAll(ArrayList(it))
            }
            this.notifyItemInserted(itemViewList.size)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsItemsBindingViewHolder =
        ComicsItemsBindingViewHolder(
            ComicsItemsBinding.inflate(
                parent.getLayoutInflater(),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ComicsItemsBindingViewHolder, position: Int) =
        holder.bind(itemViewList[position])

    override fun getItemCount(): Int = itemViewList.size

}

class ComicsItemsBindingViewHolder(
    private val comicsItemsBinding:
    ComicsItemsBinding
) : RecyclerView.ViewHolder(comicsItemsBinding.root) {
    fun bind(
        itemView: ItemView
    ) {
        comicsItemsBinding.item = itemView.name
    }
}