package com.ronny.marvel.presentation.charactersdetail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ronny.marvel.common.extensions.getLayoutInflater
import com.ronny.marvel.databinding.ComicsItemsBinding
import com.ronny.marvel.presentation.model.ItemView

class ItemsAdapter : RecyclerView.Adapter<ComicsItemsBindingViewHolder>() {
    private val itemViewList: ArrayList<ItemView> = arrayListOf()

    fun updateData(items: List<ItemView>) {
        itemViewList.addAll(ArrayList(items))
        this.notifyItemInserted(itemViewList.size)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComicsItemsBindingViewHolder =
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