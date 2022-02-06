package com.ronny.marvel.features.characters.model


import com.google.gson.annotations.SerializedName

data class Series(
    @SerializedName("available")
    val available: Int? = -1,
    @SerializedName("collectionURI")
    val collectionURI: String? = "",
    @SerializedName("returned")
    val returned: Int? = -1
)