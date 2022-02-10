package com.ronny.marvel.presentation.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import com.ronny.marvel.common.util.KParcelable
import com.ronny.marvel.common.util.parcelableCreator
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Keep
@Parcelize
data class StoriesView(
    val available: Int? = -1,
    val collectionURI: String? = "",
    val items: List<ItemView>? = arrayListOf(),
    val returned: Int? = -1
) : Parcelable