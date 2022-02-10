package com.ronny.marvel.presentation.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.ronny.marvel.common.util.KParcelable
import com.ronny.marvel.common.util.parcelableCreator
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Keep
@Parcelize
data class ThumbnailView(
    val path: String? = "",
    val extension: String? = "",
) : Parcelable