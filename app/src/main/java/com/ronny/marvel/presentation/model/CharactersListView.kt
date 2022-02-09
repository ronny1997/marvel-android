package com.ronny.marvel.presentation.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class CharactersListView(
    val code: Int?= -1,
    val status: String?="",
    val copyright: String?="",
    val attributionText: String?="",
    val attributionHTML: String?="",
    val etag: String?="",
    val data: DataView? = DataView()
): Serializable