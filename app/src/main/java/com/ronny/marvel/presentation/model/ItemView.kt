package com.ronny.marvel.presentation.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable
@Keep
data class ItemView(
    val resourceURI: String?,
    val name: String?
): Serializable