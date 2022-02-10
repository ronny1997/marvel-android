package com.ronny.marvel.presentation.model
import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Keep
@Parcelize
data class DataView(
    val offset: Int? = -1,
    val limit: Int? = -1,
    val total: Int?= -1,
    val count: Int?= -1,
    val characterItem: List<CharacterView>? = listOf()
): Parcelable