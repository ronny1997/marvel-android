package com.ronny.marvel.features.characters.model


import com.google.gson.annotations.SerializedName

data class Comics(
    @SerializedName("available")
    val available: Int? = -1,
    @SerializedName("collectionURI")
    val collectionURI: String? = "",
    @SerializedName("items")
    val items: List<Item>? = arrayListOf(),
    @SerializedName("returned")
    val returned: Int? = -1
)