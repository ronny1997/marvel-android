package com.ronny.marvel.presentation.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
@Keep
@Parcelize
data class ItemView(
    val resourceURI: String?,
    val name: String?
): Parcelable