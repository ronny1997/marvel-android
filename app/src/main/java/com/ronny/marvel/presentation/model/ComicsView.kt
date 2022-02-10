package com.ronny.marvel.presentation.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class ComicsView(
    val available: Int? = -1,
    val collectionURI: String? = "",
    val items: List<ItemView>? = arrayListOf(),
    val returned: Int? = -1
) : Parcelable