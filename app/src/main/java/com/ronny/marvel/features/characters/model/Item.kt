package com.ronny.marvel.features.characters.model


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("name")
    val name: String?
)