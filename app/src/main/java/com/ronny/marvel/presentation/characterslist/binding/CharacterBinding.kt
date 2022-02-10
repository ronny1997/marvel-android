package com.ronny.marvel.presentation.characterslist.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ronny.marvel.R
import com.ronny.marvel.presentation.characterslist.CharacterAdapter
import com.ronny.marvel.presentation.model.MarvelDataView
import com.ronny.marvel.presentation.model.ThumbnailView

object CharacterBinding {

    @BindingAdapter("app:remote_url")
    @JvmStatic
    fun coilUrl(view: ImageView, thumbnail: ThumbnailView?) {
        view.load("${thumbnail?.path?.replace("http", "https")}.${thumbnail?.extension}") {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
        }
    }

    @BindingAdapter("app:characters")
    @JvmStatic
    fun characters(view: RecyclerView, marvelDataView: MarvelDataView?) {
        marvelDataView?.let {
            (view.adapter as CharacterAdapter).updateData(it)
        }
    }
}



/*@BindingAdapter("app:coil_load")
        @JvmStatic
        fun coilBackground(view: View, thumbnail: ThumbnailView?) {
            var s = ImageLoader(view.context)
            var r = ImageRequest.Builder(view.context)
                .data("${thumbnail?.path?.replace("http", "https")}.${thumbnail?.extension}")
                .allowHardware(false) // Disable hardware bitmaps.
                .placeholder(R.drawable.ic_launcher_foreground)
                .target { drawable ->
                    when (view) {
                        is ImageView -> {
                            view.setImageDrawable(drawable)
                        }
                        is MaterialCardView -> {
                            view.background = drawable
                        }
                    }
                }.build()
            s.enqueue(r)

        }*/