package com.ronny.marvel.presentation.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class MarvelDataView(
    val code: Int? = -1,
    val status: String? = "",
    val copyright: String? = "",
    val attributionText: String? = "",
    val attributionHTML: String? = "",
    val etag: String? = "",
    val data: DataView? = DataView()
) : Parcelable